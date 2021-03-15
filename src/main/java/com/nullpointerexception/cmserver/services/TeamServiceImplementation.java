package com.nullpointerexception.cmserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nullpointerexception.cmserver.model.Team;
import com.nullpointerexception.cmserver.repository.TeamRepository;

@Service
@Transactional
public class TeamServiceImplementation implements TeamService{

	@Autowired
	TeamRepository teamRepository;

	@Override
	public List<Team> getTeamsList() {
		List<Team> teams = (List<Team>) teamRepository.findAll();
		return teams;
	}

	@Override
	public Team getTeamById(int id) {
		return teamRepository.findById(id).get();
	}

	@Override
	public Team getTeamByCode(String code) {
		return teamRepository.findByCode(code);
	}

	@Override
	public void addTeam(Team team){
		teamRepository.save(team);
		
	}

	@Override
	public void deleteTeamById(int id) {
		teamRepository.deleteById(id);
		
	}

	@Override
	public int getTeamsCount() {
		return (int) teamRepository.count();
	}
	
	@Override
	public Team getTeamByName(String name) {
		return teamRepository.findByName(name);
	}

}