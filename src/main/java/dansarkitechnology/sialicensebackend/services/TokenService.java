package dansarkitechnology.sialicensebackend.services;


import dansarkitechnology.sialicensebackend.data.Token;

import java.util.Optional;

public interface TokenService {

    Token saveToken(Token token);

    Optional<Token> findTokenByUserEmailAddress(String emailAddress);

    Optional<Token> findTokenByJwt(String jwt);
}
