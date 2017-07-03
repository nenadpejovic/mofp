package org.silab.mofp.businesslogic.sender;

import org.openqa.selenium.WebDriver;
import org.silab.mofp.config.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;



@Service
public class DataLoader {
	
	@Autowired
	private DataParser parser;
	
	@Autowired
	private JsonObject message;
	
	@Autowired
	private ConfigurationService config;
	
    
	public void startScraping(){
		parser.opetTestSite();
	}
	
	public JsonObject setMessage(){
		message.add("home players", parser.parsePlayers(config.getHomePlayerRowId(), false));
		message.add("away players", parser.parsePlayers(config.getAwayPlayerRowId(), true));
		message.add("game", parser.parseGame());
		return message;
	}
	
	public void stopScraping(){
		parser.close();
	}
}
