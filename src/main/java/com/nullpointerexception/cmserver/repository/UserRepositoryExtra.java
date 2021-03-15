package com.nullpointerexception.cmserver.repository;

import java.util.List;

import com.nullpointerexception.cmserver.model.User;

public interface UserRepositoryExtra<T,S> {
	public User findByEmail(String findByEmail);
	public List<User> findByTeamID(int teamID);

}
