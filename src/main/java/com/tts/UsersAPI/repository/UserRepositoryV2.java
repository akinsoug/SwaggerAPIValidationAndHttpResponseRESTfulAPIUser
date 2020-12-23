package com.tts.UsersAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.UsersAPI.model.UserV2;

@Repository
public interface UserRepositoryV2 extends CrudRepository<UserV2, Long> {
	UserV2 findByFirstName(String firstName);
	List<UserV2> findAllById(Iterable<Long> ids);
	List<UserV2> findByState(String state);

//	User findById2(Long id);
}