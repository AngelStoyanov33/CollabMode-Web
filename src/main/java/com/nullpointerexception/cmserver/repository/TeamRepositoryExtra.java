package com.nullpointerexception.cmserver.repository;

import com.nullpointerexception.cmserver.model.Team;

public interface TeamRepositoryExtra <T,S> {
	public Team findByCode(String findByCode);
	public Team findByName(String findByName);

}
