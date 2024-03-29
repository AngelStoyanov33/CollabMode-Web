package com.nullpointerexception.cmserver.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nullpointerexception.cmserver.model.Team;
import com.nullpointerexception.cmserver.model.User;
import com.nullpointerexception.cmserver.services.JWTManager;
import com.nullpointerexception.cmserver.services.StringRandomizer;
import com.nullpointerexception.cmserver.services.TeamService;
import com.nullpointerexception.cmserver.services.UserService;
import com.nullpointerexception.cmserver.services.XMLManager;

@RestController
public class CreateTeamController {
	@Autowired
	TeamService teamService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/createTeam", produces = {"application/json"})
	public String createATeam(@RequestBody Map<String, Object> payload) {
		JSONObject json = new JSONObject();
		JWTManager jwtManager = new JWTManager();
		XMLManager xmlManager = new XMLManager();
		if(jwtManager.verifyAToken(payload.get("token").toString())) {
			if(teamService.getTeamByName(payload.get("teamName").toString()) == null) {
				User user = userService.getUserByEmail(jwtManager.decodeAToken(payload.get("token").toString()));
				Team team = new Team(payload.get("teamName").toString(), StringRandomizer.getRandomString(7));
				if(user != null){
					teamService.addTeam(team);
					user.setTeamID(team.getId());
					user.setOwner(true);
					userService.addUser(user);
					try {
						xmlManager.addGroup(payload.get("teamName").toString());
						xmlManager.addUser(payload.get("teamName").toString(), payload.get("teamName").toString(), "", "");
						xmlManager.reloadConfiguration();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					try {
						Process p = Runtime.getRuntime().exec(new String[]{"C:\\Program Files\\mosquitto\\mosquitto_passwd.exe", "-b passwds.txt " + team.getName() + " "+ team.getCode().toString()});
						System.out.println("C:\\Program Files\\mosquitto\\mosquitto_passwd.exe"+" -b passwds.txt " + team.getName() + " " + team.getCode().toString());
						System.out.println(p.waitFor());
						
					
						
						BufferedReader stdInput = new BufferedReader(new 
							     InputStreamReader(p.getInputStream()));

							BufferedReader stdError = new BufferedReader(new 
							     InputStreamReader(p.getErrorStream()));

							// Read the output from the command
							System.out.println("Here is the standard output of the command:\n");
							String s = null;
							while ((s = stdInput.readLine()) != null) {
							    System.out.println(s);
							}

							// Read any errors from the attempted command
							System.out.println("Here is the standard error of the command (if any):\n");
							while ((s = stdError.readLine()) != null) {
							    System.out.println(s);
							}
					} catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					json.put("status", "ok");
				}
			}else {
				json.put("status", "error");
				json.put("errorMessage", "Team with that name already exists!");
			}
		}else {
			json.put("status", "error");
			json.put("errorMessage", "Unauthorized request");
		}
				
		return json.toString();
		
	}
}
