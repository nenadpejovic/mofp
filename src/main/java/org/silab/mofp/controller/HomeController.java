package org.silab.mofp.controller;

import java.util.Map;

import org.silab.mofp.businesslogic.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
	
	private String indicator =  "stop";
	
	@Autowired
	private MessageSender sender;
	
	@RequestMapping("/home")
	public String getHome(Map<String, Object> model){
		
		return "home";
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
}
