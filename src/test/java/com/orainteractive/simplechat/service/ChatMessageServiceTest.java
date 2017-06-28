package com.orainteractive.simplechat.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.orainteractive.simplechat.domain.Chat;
import com.orainteractive.simplechat.domain.ChatMessage;
import com.orainteractive.simplechat.domain.User;
import com.orainteractive.simplechat.repository.ChatMessageRepository;

@RunWith(SpringRunner.class)
public class ChatMessageServiceTest {
	@Mock
	private ChatMessageRepository chatMessageRepository;
	@InjectMocks
	private ChatMessageService chatMessageService = new ChatMessageServiceImpl();
	private User user;
	private Chat chat;
	private Chat chat2;
	

	/**
	 * using BeforeClass will execute the test once before other Tests
	 */
	@BeforeClass
	public static void setUp() {
		MockitoAnnotations.initMocks(ChatMessageServiceTest.class);		
	}
	
	@Before
	public void initData() {
		user = new User(1L, "jim", "password", "Josh", "Impossible", "cleang@mum.edu");
		chat = new Chat(1L, user, "First post msg", new Date());
		chat = new Chat(2L, user, "Second chat", new Date());
	}
	

	@Test
	public void findAll() {
		List<ChatMessage> messages = new ArrayList<>();

		messages.add(new ChatMessage(1L, user, chat, "First Chat Reply 1", new Date()));
		messages.add(new ChatMessage(2L, user, chat, "First Chat Reply 2", new Date()));
		messages.add(new ChatMessage(3L, user, chat2, "Second Chat Reply 1", new Date()));
		when(chatMessageRepository.findAll()).thenReturn(messages);

		assertEquals(3, chatMessageService.getAll().size());
	}

	@Test
	public void findById() {
		ChatMessage message = new ChatMessage(1L, user, chat, "First", new Date());
		chatMessageService.save(message);
		verify(chatMessageRepository, times(1)).save(message);
		when(chatMessageService.getById(1L)).thenReturn(message);

		ChatMessage result = chatMessageService.getById(1L);
		assertEquals(1, result.getId().longValue());		
		assertEquals("First", result.getMessage());
	}

	@Test
	public void deleteUserById() {
		ChatMessage message = new ChatMessage(1L, user, chat, "First", new Date());
		chatMessageService.remove(message.getId());
		verify(chatMessageRepository, times(1)).delete(message.getId());
	}
	
	@Test
	public void sortDateTest() {		
		List<ChatMessage> messages = new ArrayList<>();
		
		messages.add(new ChatMessage(1L, user, chat, "python", new Date(System.currentTimeMillis() - 100000)));
		messages.add(new ChatMessage(2L, user, chat, "Java", new Date()));
		messages.add(new ChatMessage(3L, user, chat2, "SQL", new Date(System.currentTimeMillis() - 300000)));
		when(chatMessageRepository.findAll()).thenReturn(messages);
		messages.forEach(s-> chatMessageService.save(s));
		
		Page<ChatMessage> page = chatMessageService.findSortedPaginatedByDate(0, 50);
		Pageable pageable = new PageRequest(0, 50, new Sort(new Sort.Order(Sort.Direction.DESC, "postedDate")));
		verify(chatMessageRepository,times(1)).findAll(pageable);
		
		System.out.println("Page " + page);
	}
}
