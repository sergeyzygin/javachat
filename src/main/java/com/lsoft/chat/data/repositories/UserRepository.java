package com.lsoft.chat.data.repositories;

import com.lsoft.chat.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
