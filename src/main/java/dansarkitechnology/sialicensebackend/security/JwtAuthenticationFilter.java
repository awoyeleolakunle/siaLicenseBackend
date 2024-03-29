package dansarkitechnology.sialicensebackend.security;


import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.exceptions.SessionTimeOutException;
import dansarkitechnology.sialicensebackend.services.tokenService.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authHeader==null || !authHeader.startsWith(GenerateApiResponse.BEARER)){
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        username =  jwtService.extractUsername(jwt);


        if(username!= null && SecurityContextHolder.getContext().getAuthentication()== null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            boolean isValidToken =
                    tokenService.findTokenByJwt(jwt).map(token -> !token.isExpired() && !token.isRevoked()).orElse(false);


            if(jwtService.isTokenValid(jwt, userDetails) && isValidToken){


                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }else {
                throw new SessionTimeOutException(GenerateApiResponse.SESSION_TIME_OUT);
            }
        }
        filterChain.doFilter(request, response);
    }

//
//    private ApiResponse sessionexpired() {
//        return ApiResponse.builder()
//                .data("Session Expired, Kindly Log In Your Account ")
//                .httpStatus(HttpStatus.FORBIDDEN)
//                .statusCode(HttpStatus.FORBIDDEN.value())
//                .isSuccessful(false)
//                .build();
//    }
}
