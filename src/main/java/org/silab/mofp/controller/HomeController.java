package org.silab.mofp.controller;

import java.util.Map;

import org.silab.mofp.businesslogic.sender.MessageSender;
import org.silab.mofp.config.ConfigurationService;
import org.silab.mofp.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@Autowired
	ConfigurationService config;
	
	private String indicator =  "stop";
	
	@Autowired
	private MessageSender sender;
	
	@RequestMapping("/home")
	public ModelAndView getHome(){
		
		return new ModelAndView("home");
	}
	
	@RequestMapping("/processing")
	@ResponseBody
	public String setProcessing(){
		if(indicator.equals("stop")){
			indicator = "start";
			sender.synchronize();
		} else {
			indicator = "stop";
			sender.stop();
		}
		
		return indicator;
		
	}
	
	@RequestMapping("/test")
	public ModelAndView getTest(){
		return new ModelAndView("test");
	}
	
	@RequestMapping(value="/send", method=RequestMethod.POST)
	@ResponseBody
	public String sendMessage(@RequestBody MessageDto message){
		sender.send(message.getMessage(), config.getQueue());
		return "ok";
	}
}
