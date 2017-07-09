package org.silab.mofp.businesslogic.sender;

import org.silab.mofp.config.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

@Component
public class MessageSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private DataLoader dataLoader;
	
	@Autowired
	private ConfigurationService config;
	
	@Autowired
	private JsonObject message;
	
	private boolean indicator=true;

	
	private void send(String message, String destination){
		LOGGER.info("sending message='{}' to destination='{}'", message, destination);
		jmsTemplate.convertAndSend(destination,message);
	}
	
	public void synchronize(){
		dataLoader.startScraping();
		while(indicator){
			
			dataLoader.setMessage();
			send(message.toString(), config.getQueue());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void stop(){
		indicator = false;
		dataLoader.stopScraping();
	}
	
	
}
