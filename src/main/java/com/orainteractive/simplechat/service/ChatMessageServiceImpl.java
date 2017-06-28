package com.orainteractive.simplechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.orainteractive.simplechat.domain.ChatMessage;
import com.orainteractive.simplechat.repository.ChatMessageRepository;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
	@Autowired
	private ChatMessageRepository chatMessageRepository;

	@Override
	public ChatMessage getById(Long id) {
		return chatMessageRepository.findOne(id);
	}

	@Override
	public void remove(Long id) {
		chatMessageRepository.delete(id);
	}

	@Override
	public ChatMessage save(ChatMessage chatMessage) {
		return chatMessageRepository.save(chatMessage);
	}

	@Override
	public List<ChatMessage> getAll() {
		return (List<ChatMessage>) chatMessageRepository.findAll();
	}

	@Override
	public Page<ChatMessage> findPaginated(Pageable pageable) {
		return chatMessageRepository.findAll(pageable);
	}

	@Override
	public Page<ChatMessage> findSortedPaginatedByDate(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(new Sort.Order(Sort.Direction.DESC, "postedDate")));
		return chatMessageRepository.findAll(pageable);
	}
}
