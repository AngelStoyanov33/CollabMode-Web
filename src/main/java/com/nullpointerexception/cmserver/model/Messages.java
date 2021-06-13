package com.nullpointerexception.cmserver.model;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.nullpointerexception.cmserver.services.UserService;

@Entity
@Table(name = "messages")
public class Messages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="content")
    private String content;
	
	@Column(name="senderId")
    private int senderId;
    
	@Column(name="topic")
    private String topic;
	
	
	public Messages(String content, int senderId, String topic) {
		this.content = content;
		this.senderId = senderId;
		this.topic = topic;
	}
	
	public Messages() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSender(int senderId) {
        this.senderId = senderId;
    }
    
    public String getTopic() {
    	return this.topic;
    }
    
    public void setTopic(String topic) {
    	this.topic = topic;
    }

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("sender_id", this.getSenderId());
		json.put("content", this.getContent());
		json.put("topic", this.getTopic());
		return json.toString();
	}
    

    
}

