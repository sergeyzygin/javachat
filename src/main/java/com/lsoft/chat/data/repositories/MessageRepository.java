package com.lsoft.chat.data.repositories;

import com.lsoft.chat.data.models.Message;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findByChatId(int chatId, Sort sort);
}
