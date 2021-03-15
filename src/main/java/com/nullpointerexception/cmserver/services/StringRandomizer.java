package com.nullpointerexception.cmserver.services;

public class StringRandomizer {
	public static String getRandomString(final int lenght) {
		final String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
		StringBuilder stringBuilder = new StringBuilder(lenght);
		for(int i = 0; i < lenght; i++) {
			stringBuilder.append(symbols.charAt((int) (symbols.length() * Math.random())));
		}
		return stringBuilder.toString();
	}

}
