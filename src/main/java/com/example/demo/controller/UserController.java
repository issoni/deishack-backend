package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.object.User;
import com.example.demo.service.FirebaseServices;

@RestController
public class UserController {
	
	@Autowired
	private FirebaseServices firebaseServices;
	
	@GetMapping("/user")
	public User getUserDetails(@RequestParam("id") String id) throws InterruptedException, ExecutionException {
		return firebaseServices.getUser(id);
	}
	
	@PostMapping("/user")
	public String createNewUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		return firebaseServices.saveUser(user);
	}
	
	@PutMapping("/user")
	public String updateUser(@RequestBody User user) {
		return "Updated user "+ user.getName();
	}
	
	@DeleteMapping("/user")
	public String deleteUser(@RequestHeader String name) throws InterruptedException, ExecutionException {
		return firebaseServices.deleteUser(name);
	}

}
