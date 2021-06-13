package com.nullpointerexception.cmserver.repository;

import java.util.List;

import com.nullpointerexception.cmserver.model.Messages;

public interface MessagesRepositoryExtra<T,S> {
	public List<Messages> findByTopic(String topic);
	public List<Messages> findBySenderId(int senderId);

}
