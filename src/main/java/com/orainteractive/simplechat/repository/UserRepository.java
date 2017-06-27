package com.orainteractive.simplechat.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.orainteractive.simplechat.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findFirstByUsername(String username);
}
