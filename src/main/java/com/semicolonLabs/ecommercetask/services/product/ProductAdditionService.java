package com.semicolonLabs.ecommercetask.services.product;


import com.semicolonLabs.ecommercetask.data.models.Product;
import com.semicolonLabs.ecommercetask.data.models.Seller;
import com.semicolonLabs.ecommercetask.data.models.Store;
import com.semicolonLabs.ecommercetask.data.models.enums.ProductCategory;
import com.semicolonLabs.ecommercetask.dtos.request.ProductAdditionRequest;
import com.semicolonLabs.ecommercetask.exceptions.ProductException;
import com.semicolonLabs.ecommercetask.exceptions.SellerException;
import com.semicolonLabs.ecommercetask.exceptions.StoreException;
import com.semicolonLabs.ecommercetask.services.seller.SellerService;
import com.semicolonLabs.ecommercetask.services.store.StoreService;
import com.semicolonLabs.ecommercetask.utils.ApiResponse;
import com.semicolonLabs.ecommercetask.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductAdditionService {
    private ProductService productService;
    private SellerService sellerService;

    private StoreService storeService;

//   private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public ApiResponse addProductToStore(ProductAdditionRequest productAdditionRequest) throws SellerException, StoreException, ProductException {

        Optional<Seller> foundSeller = sellerService.findByEmail(productAdditionRequest.getSellerEmailAddress());
        if(foundSeller.isEmpty()) throw new SellerException(GenerateApiResponse.INVALID_DETAILS);

        Store store = foundSeller.get().getStore();
        if(store==null) throw new StoreException(GenerateApiResponse.STORE_NOT_FOUND);

        Product savedProduct = addNewProductToSellerStore(productAdditionRequest);
        Store updatedStoreWithNewProduct = addProductToSellerStore(store, savedProduct);

        updateSellerStore(foundSeller.get(), updatedStoreWithNewProduct);

        return GenerateApiResponse.created(GenerateApiResponse.PRODUCT_ADDED_SUCCESSFULLY);
    }

    private void updateSellerStore(Seller seller, Store updatedStoreWithNewProduct) {
        seller.setStore(updatedStoreWithNewProduct);
        sellerService.saveSeller(seller);
    }

    private Store addProductToSellerStore(Store store, Product savedProduct) {
        Set<Product> setOfProducts = store.getSetOfProducts();
        if (setOfProducts == null) {
            setOfProducts = new HashSet<>();
        }
        System.out.println(store);
        setOfProducts.add(savedProduct);
        store.setSetOfProducts(new HashSet<>(setOfProducts));
        return storeService.save(store);
    }

    private Product addNewProductToSellerStore(ProductAdditionRequest productAdditionRequest)  throws ProductException {
        System.out.println(productAdditionRequest.getProductName());
        System.out.println(productAdditionRequest.getSellerEmailAddress());
        Optional<Product> foundProduct = productService.findProductByName(productAdditionRequest.getProductName());
        System.out.println(foundProduct);
        if (foundProduct.isPresent()) throw new ProductException(GenerateApiResponse.PRODUCT_ALREADY_ADDED);
        Product product = new Product();
        product.setProductName(productAdditionRequest.getProductName());
        product.setProductCategory(productAdditionRequest.getProductCategory().toUpperCase());
       // product.setPrice(BigDecimal.valueOf((productAdditionRequest.getProductPrice())));
        return productService.save(product);
    }

}
