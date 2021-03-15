package com.nullpointerexception.cmserver.repository;

import org.springframework.data.repository.CrudRepository;

import com.nullpointerexception.cmserver.model.Team;

public interface TeamRepository extends CrudRepository<Team, Integer>, TeamRepositoryExtra<Team, Integer>{

}