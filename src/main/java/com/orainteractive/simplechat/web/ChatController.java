package com.orainteractive.simplechat.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orainteractive.simplechat.constant.SimpleChatConstant;
import com.orainteractive.simplechat.domain.Chat;
import com.orainteractive.simplechat.exception.BaseException;
import com.orainteractive.simplechat.exception.ChatException;
import com.orainteractive.simplechat.helper.ResponseHelper;
import com.orainteractive.simplechat.po.CustomResponse;
import com.orainteractive.simplechat.service.ChatService;

@RestController
public class ChatController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	@Autowired
	private ChatService chatService;

	@RequestMapping(value = "/chats", method = RequestMethod.GET)
	public ResponseEntity<CustomResponse<Chat>> getChats() {
		logger.info("ChatController.clazz getChats()");
		Pageable pageable = new PageRequest(0, SimpleChatConstant.PER_PAGE);
		Page<Chat> list = chatService.findPaginated(pageable);
		CustomResponse<Chat> result = ResponseHelper.convertFromPage(list, pageable.getPageNumber(),
				pageable.getPageSize());
		
		return new ResponseEntity<CustomResponse<Chat>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/chats", params = {"page"}, method = RequestMethod.GET)
	public @ResponseBody CustomResponse<Chat> getPageChats(@RequestParam("page") int page) {
		logger.info("ChatController.clazz getPageChats()");

		Page<Chat> list = chatService.getPageSortByDate(page, SimpleChatConstant.PER_PAGE);
		CustomResponse<Chat> result = ResponseHelper.convertFromPage(list, page, SimpleChatConstant.PER_PAGE);
		
		return result;
	}

	@RequestMapping(value = "/chats/{id}", method = RequestMethod.GET)
	public ResponseEntity<Chat> getChat(@PathVariable("id") long id) throws ChatException {
		logger.info("ChatController.clazz getChat() id " + id);

		if (id <= 0 || chatService.getById(id) == null) {
			logger.info("ChatController.clazz getChat() can not be found with id::" + id);
			throw new ChatException("Chat id can not be found!");
		}
		return new ResponseEntity<Chat>(chatService.getById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/chats", method = RequestMethod.POST)
	public ResponseEntity<Chat> save(@Valid @RequestBody Chat chat) throws BaseException {
		logger.info("ChatController.clazz save() chat" + chat);
		return new ResponseEntity<Chat>(chatService.save(chat), HttpStatus.OK);
	}

	@RequestMapping(value = "/chats", method = RequestMethod.PATCH)
	public ResponseEntity<Chat> update(@Valid @RequestBody Chat chat, HttpServletRequest req) throws BaseException {
		logger.info("UserController.clazz update() " + chat);
		
		verifyPermission(req, chat.getOwner().getId().toString());
		
		if (chat.getId() <= 0 || chatService.getById(chat.getId()) == null) {
			logger.info("ChatController.clazz update() can't update chat with message " + chat.getMessage());
			throw new ChatException("Failed, chat doesn't exist");
		}
		return new ResponseEntity<Chat>(chatService.save(chat), HttpStatus.OK);
	}

	@RequestMapping(value = "/chats", method = RequestMethod.PUT)
	public ResponseEntity<Chat> updateEntity(@Valid @RequestBody Chat chat, HttpServletRequest req) throws BaseException {
		logger.info("UserController.clazz updateEntity() " + chat);

		verifyPermission(req, chat.getOwner().getId().toString());
		
		if (chat.getId() <= 0 || chatService.getById(chat.getId()) == null) {
			logger.info("ChatController.clazz updateEntity() can't update chat with message " + chat.getMessage());
			throw new ChatException("Failed, chat doesn't exist");
		}
		return new ResponseEntity<Chat>(chatService.save(chat), HttpStatus.OK);
	}
	
	
	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) {
	 * binder.registerCustomEditor(User.class, "owner", new PropertiesEditor() {
	 * 
	 * @Override public void setAsText(String id) throws
	 * IllegalArgumentException { User uzer =
	 * userService.getById(Long.parseLong(id)); setValue(uzer); }
	 * 
	 * @Override public String getAsText() { User uzer = (User) getValue(); if
	 * (uzer == null) return null; return (uzer.getId() == null) ? null :
	 * String.valueOf(uzer.getId()); } }); }
	 */
}
