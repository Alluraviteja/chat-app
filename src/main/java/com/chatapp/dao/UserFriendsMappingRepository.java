package com.chatapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.User;
import com.chatapp.entity.UserFriendsMapping;


@Repository
public interface UserFriendsMappingRepository extends JpaRepository<UserFriendsMapping, Long>, 
								JpaSpecificationExecutor<UserFriendsMapping>,PagingAndSortingRepository<UserFriendsMapping, Long> {

	List<UserFriendsMapping> findByUserMain(User user);
	
	@Query(nativeQuery = true, value = "select * from user_friends where user_id = ?1 and friend_user_id = ?2")
	Optional<UserFriendsMapping> findByUserMainAndUserFriend(User user, User userFriend);
	
}
