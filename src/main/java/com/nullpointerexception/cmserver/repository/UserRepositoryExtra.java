package com.nullpointerexception.cmserver.repository;

import com.nullpointerexception.cmserver.model.User;

public interface UserRepositoryExtra<T,S> {
	public User findByEmail(String findByEmail);

}
