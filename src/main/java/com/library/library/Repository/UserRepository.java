package com.library.library.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.library.library.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User getById(Integer id);
	
	
	
	@Query(value = "select u from User u  where u.id =:userId")
	User getAuthorDetail(@Param("userId") Integer id);

	@Query(value="select n from User n where n.id=:userId")
	List<User> findAllByUserId(@Param("userId") Integer id);
	
	@Query("Select count(id) from User")
	Long totalCount();
}
