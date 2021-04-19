package com.nullpointerexception.cmserver.socket;


import java.io.IOException;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class WebSocketHandler extends AbstractWebSocketHandler  {
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
	    System.out.println("[TM] - RECEIVED : " + message + "\n Returning it to sender...");
	    session.sendMessage(message);
	}

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
		System.out.println("[BM] - RECEIVED : " + message + "\n Returning it to sender...");
	    session.sendMessage(message);
	}
}
