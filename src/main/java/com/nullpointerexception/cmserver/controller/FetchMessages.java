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
		for(int i = 0; i < messagesArray.length(); i++) {
			JSONObject t = messagesArray.getJSONObject(i);
			t.remove("id");
			int senderId = (int) t.get("senderId");
			t.remove("senderId");
			User user = userService.getUserById(senderId);
			t.put("sender", user.getFullName());
			
		}
		json.put("status", "ok");
		json.put("messages", messagesArray.toString());
		
		return messagesArray.toString();	
	
//		else {
//			json.put("status", "error");
//			json.put("errorMessage", "Unauthorized request");
//		}
		//json.put("status", "ok");
		
		//return json.toString();
	}

}

