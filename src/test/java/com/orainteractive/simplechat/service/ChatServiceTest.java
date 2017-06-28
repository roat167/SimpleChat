package com.orainteractive.simplechat.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.orainteractive.simplechat.domain.Chat;
import com.orainteractive.simplechat.domain.User;
import com.orainteractive.simplechat.repository.ChatRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ChatServiceTest {
	@Mock
	private ChatRepository chatRepository;
	@InjectMocks
	private ChatService chatService = new ChatServiceImpl();
	private static User user;

	/**
	 * using BeforeClass will execute the test once before other Tests
	 */
	@BeforeClass
	public static void setUp() {
		MockitoAnnotations.initMocks(ChatServiceTest.class);
		user = new User(1L, "jim", "password", "Josh", "Impossible", "cleang@mum.edu");
	}

	@Test
	public void findAll() {
		List<Chat> chats = new ArrayList<>();

		chats.add(new Chat(1L, user, "First post msg", new Date()));
		chats.add(new Chat(2L, user, "Second", new Date()));
		when(chatRepository.findAll()).thenReturn(chats);

		assertEquals(2, chatService.getAll().size());
	}

	@Test
	public void findById() {
		Chat chat = new Chat(1L, user, "First", new Date());
		chatService.save(chat);
		verify(chatRepository, times(1)).save(chat);
		when(chatService.getById(1L)).thenReturn(chat);

		Chat result = chatService.getById(1L);
		assertEquals(1, result.getId().longValue());
		assertEquals("First", result.getMessage());
	}

	@Test
	public void deleteUserById() {
		Chat chat = new Chat(1L, user, "First", new Date());
		chatService.remove(chat.getId());
		verify(chatRepository, times(1)).delete(chat.getId());
	}
	
	@Test
	public void sortDateTest() {		
		List<Chat> chats = new ArrayList<>();
		
		chats.add(new Chat(1L, user, "ABC", new Date(System.currentTimeMillis() - 100000)));
		chats.add(new Chat(2L, user, "Java", new Date()));
		chats.add(new Chat(3L, user, "SQL", new Date(System.currentTimeMillis() - 300000)));
		when(chatRepository.findAll()).thenReturn(chats);
		chats.forEach(s-> chatService.save(s));
		
		chatService.getPageSortByDate(0, 50);
		Pageable pageable = new PageRequest(0, 50, new Sort(new Sort.Order(Sort.Direction.DESC, "postedDate")));
		verify(chatRepository,times(1)).findAll(pageable);
		
//		System.out.println("Normal page " + chatService.listAllByPage(new PageRequest(0, 50)));
//		List<Chat> sortList = page.getContent();		
//		
//		assertEquals(3, sortList.size());
//		assertThat(sortList.get(0)).extracting("Java").contains(sortList.get(0).getMessage());
	}
}
