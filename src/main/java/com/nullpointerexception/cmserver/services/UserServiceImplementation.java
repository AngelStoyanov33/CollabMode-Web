package com.nullpointerexception.cmserver.services;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nullpointerexception.cmserver.model.User;
import com.nullpointerexception.cmserver.repository.UserRepository;

@Service
@Transactional
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getUsersList() {
		List<User> users = (List<User>) userRepository.findAll();
		return users;
	}

	@Override
	public User getUserById(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void addUser(User user){
		userRepository.save(user);
	}

	@Override
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public int getUsersCount() {
		return (int) userRepository.count();
	}

	@Override
	public List<User> getUsersByTeamID(int teamID) {
		return (List<User>) userRepository.findByTeamID(teamID);
	}

}
