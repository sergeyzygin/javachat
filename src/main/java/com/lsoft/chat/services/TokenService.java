package com.lsoft.chat.services;

import com.lsoft.chat.data.User;
import com.lsoft.chat.data.models.Token;
import com.lsoft.chat.data.repositories.TokenRepository;
import com.lsoft.chat.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class TokenService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    private AuthService authService;

    public boolean addToken(String tokenValue){
        User currentUser = authService.getCurrentUser();
        AtomicBoolean exist = new AtomicBoolean(false);
        if (currentUser != null){
            List<Token> tokens =  tokenRepository.findByUserId(currentUser.getId());
            tokens.forEach(token -> {
                if (tokenValue.equals(token.getToken())) exist.set(true);
            });

            if (exist.get() == false){
                Token token = new Token();
                token.setUserId(currentUser.getId());
                token.setToken(tokenValue);
                tokenRepository.save(token);
                return true;
            }
        }
        return false;

    }
}
