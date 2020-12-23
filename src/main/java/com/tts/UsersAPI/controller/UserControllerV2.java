package com.tts.UsersAPI.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tts.UsersAPI.model.UserV2;
import com.tts.UsersAPI.repository.UserRepositoryV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value="userApi", description="Operations pertaining to UserApi")
@RestController
@RequestMapping("/v2")
public class UserControllerV2 {
	
	@Autowired
	UserRepositoryV2 repository; 


	@ApiOperation(value = "Get all users", response = UserV2.class, responseContainer = "List")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully retrieved users"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the users"),
		    @ApiResponse(code = 400, message = "Error in the request. User State must present"),
		})
	@GetMapping("/users/bystate")
	public ResponseEntity<List<UserV2>> getUsersByState(@RequestParam(value = "state", required = true) @Valid String state) {
		if (state == null) {
			return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
		return  new ResponseEntity<>((List<UserV2>) repository.findByState(state), HttpStatus.OK);
	} //public ResponseEntity<List<User>> getUsers(@RequestParam(value = "state", required = false) @Valid String state, BindingResult bindingResult) {

	

	@ApiOperation(value = "Get all users", response = UserV2.class, responseContainer = "List")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully retrieved users"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the users"),
		    @ApiResponse(code = 400, message = "Error in the request. User State must present"),
		})
	@GetMapping("/users")
	public ResponseEntity<List<UserV2>> getUsers() {
		return  new ResponseEntity<>((List<UserV2>) repository.findAll(), HttpStatus.OK); // 
	} //public ResponseEntity<List<User>> getUsers(@RequestParam(value = "state", required = false) @Valid String state, BindingResult bindingResult) {

	

	/**
	 * 
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@ApiOperation(value = "Post(create) a new user", response = UserV2.class, responseContainer = "Void")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
		    @ApiResponse(code = 201, message = "User Successfully created"),
		    @ApiResponse(code = 400, message = "Error in the request. User cannot be created"),
		})
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody @Valid UserV2 user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		repository.save(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
 /**
  * 
  * @param id
  * @param user
  * @param bindingResult
  * @return
  */
	@ApiOperation(value = "Post(create) a new user", response = UserV2.class, responseContainer = "Void")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
		    @ApiResponse(code = 201, message = "User Successfully updated"),
		    @ApiResponse(code = 400, message = "Error in the request. User cannot be updated"),
//		    @ApiResponse(code = 401, message = "You are not authorized to updat a user"),
		    @ApiResponse(code = 404, message = "User not Found"),
		})
	@PutMapping("/users/{id}")
	public ResponseEntity<Void>  updateUser(@PathVariable(value = "id") Long id, @RequestBody @Valid UserV2 user, BindingResult bindingResult) {
		Optional<UserV2> user2 = repository.findById(id);
		System.out.println("\n\n user: \t" + user + "\t user2: \t" + user2);
		
		if (!user2.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else if (bindingResult.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		repository.save(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@ApiOperation(value = "Post(create) a new user", response = UserV2.class, responseContainer = "User")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
		    @ApiResponse(code = 201, message = "User Successfully retreived"),
		    @ApiResponse(code = 400, message = "Error in the request. User cannot be retreived"),
//		    @ApiResponse(code = 401, message = "You are not authorized to updat a user"),
		    @ApiResponse(code = 404, message = "User not Found"),
		})
	@GetMapping("/users/{id}")
	public ResponseEntity<Optional<UserV2>> getUserById(@PathVariable(value = "id") Long id) {
		Optional<UserV2> user2 = repository.findById(id);
		if (!user2.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
//		if (!user.isPresent()) {
//			
//		}
		
		return new ResponseEntity<>(user2, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Post(create) a new user", response = UserV2.class, responseContainer = "User")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
		    @ApiResponse(code = 404, message = "User not Found"),
		})
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) {

		Optional<UserV2> user2 = repository.findById(id);
		if (!user2.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		repository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}