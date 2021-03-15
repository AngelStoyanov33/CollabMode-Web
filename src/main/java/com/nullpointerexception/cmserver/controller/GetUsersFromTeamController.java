package com.nullpointerexception.cmserver.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.nullpointerexception.cmserver.model.User;
import com.nullpointerexception.cmserver.services.JWTManager;
import com.nullpointerexception.cmserver.services.TeamService;
import com.nullpointerexception.cmserver.services.UserService;

@RestController
public class GetUsersFromTeamController {

	@Autowired
	TeamService teamService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/getUsersFromTeam", produces = {"application/json"})
	public String getUsersFromTeam(@RequestBody Map<String, Object> payload) {
		
		JSONObject json = new JSONObject();
		JWTManager jwtManager = new JWTManager();
		if(jwtManager.verifyAToken(payload.get("token").toString())) {
			List<User> users = userService.getUsersByTeamID(Integer.parseInt(payload.get("teamID").toString()));
			if(users != null) {
				JSONArray userArray = new JSONArray(new Gson().toJson(users));
				for(int i = 0; i < userArray.length(); i++) {
					JSONObject t = userArray.getJSONObject(i);
					t.remove("password");
					t.remove("newsletterStatus");
				}
				json.put("status", "ok");
				json.put("users", userArray);
				
				System.out.println(json.getJSONArray("users").get(0));
				
			}else {
				json.put("status", "error");
				json.put("errorMessage", "Invalid team code");
			}
		}else {
			json.put("status", "error");
			json.put("errorMessage", "Unauthorized request");
		}
				
		return json.toString();
		
	}
	
}
