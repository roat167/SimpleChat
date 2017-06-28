package com.orainteractive.simplechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.orainteractive.simplechat.domain.Chat;
import com.orainteractive.simplechat.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Chat getById(Long id) {
		return chatRepository.findOne(id);
	}

	@Override
	public void remove(Long id) {
		chatRepository.delete(id);
	}

	@Override
	public Chat save(Chat chat) {
		return chatRepository.save(chat);
	}

	@Override
	public List<Chat> getAll() {
		return (List<Chat>) chatRepository.findAll();
	}

	@Override
	public Page<Chat> findPaginated(Pageable pageable) {
		return chatRepository.findAll(pageable);
	}

	@Override
	public Page<Chat> getPageSortByDate(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(new Sort.Order(Sort.Direction.DESC, "postedDate")));
		return chatRepository.findAll(pageable);
	}
}
