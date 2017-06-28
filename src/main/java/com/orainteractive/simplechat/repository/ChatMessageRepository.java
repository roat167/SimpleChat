package com.orainteractive.simplechat.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.orainteractive.simplechat.domain.ChatMessage;

public interface ChatMessageRepository extends PagingAndSortingRepository<ChatMessage, Long> {

}
