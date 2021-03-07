package com.nullpointerexception.cmserver.repository;

import org.springframework.data.repository.CrudRepository;

import com.nullpointerexception.cmserver.model.User;

public interface UserRepository extends CrudRepository<User, Integer>, UserRepositoryExtra<User, Integer>{

}
