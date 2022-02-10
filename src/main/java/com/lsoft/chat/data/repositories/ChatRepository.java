package com.lsoft.chat.data.repositories;

import com.lsoft.chat.data.models.Chat;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Integer> {
    List<Chat> findByUsers_id(int userId, Sort sort);
}
