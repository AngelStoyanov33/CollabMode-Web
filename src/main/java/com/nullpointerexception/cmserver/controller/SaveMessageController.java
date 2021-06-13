package com.nullpointerexception.cmserver.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nullpointerexception.cmserver.model.Messages;
import com.nullpointerexception.cmserver.model.User;
import com.nullpointerexception.cmserver.services.MessagesService;
import com.nullpointerexception.cmserver.services.TeamService;
import com.nullpointerexception.cmserver.services.UserService;

@RestController
public class SaveMessageController {
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	MessagesService messagesService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveMessage", produces = {"application/json"})
	public String saveMessage(@RequestBody Map<String, Object> payload ) {
		JSONObject json = new JSONObject();
		
		// sender
		User user = userService.getUserByFullName(payload.get("sender").toString());
		
		//topic
		//content
		
		Messages message = new Messages(payload.get("content").toString(), 
				user.getId(), payload.get("topic").toString());
		messagesService.addMessage(message);
		
//		else {
//			json.put("status", "error");
//			json.put("errorMessage", "Unauthorized request");
//		}
		json.put("status", "ok");
		
		return json.toString();
	}

}
