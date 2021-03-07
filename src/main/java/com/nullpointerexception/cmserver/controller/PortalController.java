package com.nullpointerexception.cmserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PortalController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/imageSwitcher")
	public String openPortal() {
		return "portal";
	}
	
}
