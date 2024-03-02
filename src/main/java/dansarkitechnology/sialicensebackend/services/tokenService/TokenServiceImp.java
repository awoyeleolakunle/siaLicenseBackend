package dansarkitechnology.sialicensebackend.services.tokenService;


import dansarkitechnology.sialicensebackend.data.models.Token;
import dansarkitechnology.sialicensebackend.data.repositories.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenServiceImp implements TokenService {

    private final TokenRepository tokenRepository;

    @Override
    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public Optional<Token> findTokenByUserEmailAddress(String emailAddress) {
        return Optional.empty();
    }

    @Override
    public Optional<Token> findTokenByJwt(String jwt) {
        return Optional.empty();
    }
}
