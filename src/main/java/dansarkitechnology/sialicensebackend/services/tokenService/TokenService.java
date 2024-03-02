package dansarkitechnology.sialicensebackend.services.tokenService;


import dansarkitechnology.sialicensebackend.data.models.Token;

import java.util.Optional;

public interface TokenService {

    Token saveToken(Token token);

    Optional<Token> findTokenByUserEmailAddress(String emailAddress);

    Optional<Token> findTokenByJwt(String jwt);
}
