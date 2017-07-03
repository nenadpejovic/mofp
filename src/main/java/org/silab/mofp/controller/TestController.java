package org.silab.mofp.controller;
import org.silab.mofp.businesslogic.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/test")
public class TestController {

	@Autowired
	private MessageSender sender;
	
	@RequestMapping(value="send", method=RequestMethod.GET)
	public void sendMessage(){
		//sender.send("Test message", "test.queque");
	}
}
