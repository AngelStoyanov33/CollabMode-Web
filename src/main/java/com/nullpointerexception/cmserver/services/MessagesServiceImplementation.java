package com.nullpointerexception.cmserver.services;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nullpointerexception.cmserver.model.Messages;
import com.nullpointerexception.cmserver.repository.MessagesRepository;

@Service
@Transactional
public class MessagesServiceImplementation implements MessagesService{
	
	@Autowired
	MessagesRepository messageRepository;
	

	@Override
	public List<Messages> getMessagesByTopic(String topic) {
		List<Messages> messages = (List<Messages>) messageRepository.findByTopic(topic);
		return messages;
	}

	@Override
	public Messages getMessageById(int id) {
		return messageRepository.findById(id).get();
	}

	@Override
	public List<Messages> getMessagesBySender(int senderId) {
		return messageRepository.findBySenderId(senderId);
	}

	@Override
	public void addMessage(Messages message) {
		messageRepository.save(message);
		
	}

	@Override
	public void deleteMessagesById(int id) {
		messageRepository.deleteById(id);
		
	}

	@Override
	public int getMessagesCount() {
		return (int) messageRepository.count();
	}

}
