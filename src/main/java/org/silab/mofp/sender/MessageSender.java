package org.silab.mofp.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class MessageSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);
	
	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(String message, String destination){
		LOGGER.info("sending message='{}' to destination='{}'", message, destination);
		jmsTemplate.convertAndSend(destination,message);
	}
}
