package com.orainteractive.simplechat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orainteractive.simplechat.domain.User;
import com.orainteractive.simplechat.exception.DuplicateUsernameException;
import com.orainteractive.simplechat.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User save(User user) throws DuplicateUsernameException {
		User temp = userRepository.findFirstByUsername(user.getUsername());
		if (temp != null && !(temp.getId().equals(user.getId()))) {
			throw new DuplicateUsernameException("Username is already taken!");
		}
		return userRepository.save(user);
	}

	@Override
	public void remove(Long id) {
		userRepository.delete(id);
	}

	@Override
	public void remove(User user) {
		userRepository.delete(user);
	}

	@Override
	public User getByUsername(String username) {
		return userRepository.findFirstByUsername(username);
	}

	@Override
	public List<User> getAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public Page<User> listAllByPage(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
}
