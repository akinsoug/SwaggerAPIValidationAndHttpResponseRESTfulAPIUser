package com.tts.UsersAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.UsersAPI.model.UserV1;

@Repository
public interface UserRepositoryV1 extends CrudRepository<UserV1, Long> {
	UserV1 findByFirstName(String firstName);
	List<UserV1> findAllById(Iterable<Long> ids);
	List<UserV1> findByState(String state);

//	User findById2(Long id);
}