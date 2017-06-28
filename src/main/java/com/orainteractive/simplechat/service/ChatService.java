package com.orainteractive.simplechat.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.orainteractive.simplechat.domain.Chat;

public interface ChatService {
	Chat getById(Long id);

	void remove(Long id);

	Chat save(Chat chat);

	List<Chat> getAll();

	Page<Chat> findPaginated(Pageable pageable);	
	
	Page<Chat> getPageSortByDate(int page, int size);
}
