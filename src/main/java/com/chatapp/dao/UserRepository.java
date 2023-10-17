package com.chatapp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>,PagingAndSortingRepository<User, Long> {
	
	Optional<User> findByMobile(String mobile);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsername(String email);
	
}
