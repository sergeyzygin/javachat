package com.lsoft.chat.data.repositories;

import com.lsoft.chat.data.models.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends CrudRepository<Token, Integer> {
    List<Token> findByUserId(int userId);
}
