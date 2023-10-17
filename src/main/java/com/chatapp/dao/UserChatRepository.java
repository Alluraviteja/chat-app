package com.chatapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.UserChat;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long>, 
		JpaSpecificationExecutor<UserChat>,PagingAndSortingRepository<UserChat, Long> {
	
	@Query(nativeQuery = true, value = "select * from user_chat where user_friends_id in ("
			+ "	SELECT user_freinds_id FROM user_friends where "
			+ "		(user_id = ?1 and friend_user_id = ?2) or (user_id = ?2 and friend_user_id = ?1)"
			+ "	) order by created_date desc")
	List<UserChat> findMessagesBySenderAndMessanger(Long senderUserId, Long recieverUserId);
	
}
