package com.nullpointerexception.cmserver.services;

import java.util.List;

import com.nullpointerexception.cmserver.model.Team;

public interface TeamService {
	
	 public List<Team> getTeamsList();
	 
	 public Team getTeamById(int id);
	 
	 public Team getTeamByCode(String email);
	 
	 public Team getTeamByName(String name);
	 
	 public void addTeam(Team team);
	 
	 public void deleteTeamById(int id);
	 
	 public int getTeamsCount();

}
