package com.nullpointerexception.cmserver.services;

import java.util.List; 
import com.nullpointerexception.cmserver.model.User;


public interface UserService {
	 public List<User> getUsersList();
	 
	 public User getUserById(int id);
	 
	 public User getUserByEmail(String email);
	 
	 public User getUserByFullName(String fullname);
	 
	 public List<User> getUsersByTeamID(int teamID);
	 
	 public void addUser(User user);
	 
	 public void deleteUserById(int id);
	 
	 public int getUsersCount();

}
