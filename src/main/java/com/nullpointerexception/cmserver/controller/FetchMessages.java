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
import com.nullpointerexception.cmserver.model.Messages;
import com.nullpointerexception.cmserver.model.User;
import com.nullpointerexception.cmserver.services.MessagesService;
import com.nullpointerexception.cmserver.services.TeamService;
import com.nullpointerexception.cmserver.services.UserService;

@RestController
public class FetchMessages {
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	MessagesService messagesService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/fetchMessages", produces = {"application/json"})
	public String fetchMessages(@RequestBody Map<String, Object> payload ) {
		JSONObject json = new JSONObject();
		List<Messages> messages = messagesService.getMessagesByTopic(payload.get("topic").toString());
		JSONArray messagesArray = new JSONArray(new Gson().toJson(messages));
		User mainUser = userService.getUserByFullName(payload.get("user").toString());
		
		JSONArray messagesArrayFiltered = new JSONArray();
		
		for(int i = 0; i < messagesArray.length(); i++) {
			JSONObject t = messagesArray.getJSONObject(i);
			User user = userService.getUserById((int) t.get("senderId"));
			if(user.getTeamID() == mainUser.getTeamID()) {
				messagesArrayFiltered.put(t);
			}
		}
		
		for(int i = 0; i < messagesArrayFiltered.length(); i++) {
			JSONObject t = messagesArrayFiltered.getJSONObject(i);
			t.remove("id");
			int senderId = (int) t.get("senderId");
			t.remove("senderId");
			User user = userService.getUserById(senderId);
			t.put("sender", user.getFullName());
		}
		json.put("status", "ok");
		json.put("messages", messagesArrayFiltered.toString());
		
		return messagesArrayFiltered.toString();	
	
	}

}

