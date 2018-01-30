package com.project.dojosub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.dojosub.models.User;

@Repository 												
public interface UserRepository extends CrudRepository<User,Long>{
	User findByEmail(String email);
	@Query(value="select * from user where is_admin=0", nativeQuery=true)
    List<User> findAllUsersNotAdmin();
}
