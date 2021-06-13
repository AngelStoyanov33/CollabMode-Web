package com.nullpointerexception.cmserver.services;

import java.util.List;

import com.nullpointerexception.cmserver.model.Messages;

public interface MessagesService {
	 public List<Messages> getMessagesByTopic(String topic);
	 
	 public Messages getMessageById(int id);
	 	 
	 public List<Messages> getMessagesBySender(int senderId);
	 
	 public void addMessage(Messages message);
	 
	 public void deleteMessagesById(int id);
	 
	 public int getMessagesCount();

}


