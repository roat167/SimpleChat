package com.orainteractive.simplechat.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.orainteractive.simplechat.domain.User;
import com.orainteractive.simplechat.exception.DuplicateUsernameException;

public interface UserService {
	User getById(Long id);

	User save(User user) throws DuplicateUsernameException;

	void remove(Long id);

	void remove(User user);

	User getByUsername(String username);

	List<User> getAll();

	Page<User> listAllByPage(Pageable pageable);
}
