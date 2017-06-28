package com.orainteractive.simplechat.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.orainteractive.simplechat.domain.Chat;

public interface ChatRepository extends PagingAndSortingRepository<Chat, Long> {
}
