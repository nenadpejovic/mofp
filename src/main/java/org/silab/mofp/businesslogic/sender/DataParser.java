package org.silab.mofp.businesslogic.sender;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.silab.mofp.config.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Service
public class DataParser {

	@Autowired
	private JsonObject object;
	
	@Autowired
	private WebDriver webDriver;
	
	@Autowired
	private ConfigurationService config;
	
	public void opetTestSite(){
		webDriver.navigate().to(config.getMatchUrl());
	}
	
	public JsonArray parsePlayers(String playerRowId, boolean away){
		
		JsonArray homePlayers = new JsonArray();
		
		for(int i = 1; i<13;i++){
			String idSuffix = i < 10? "0"+i+"_row":i+"_row";
			String startingLineupIdSuffix = i < 10? "0"+i+"_lnkPlayerName":i+"_lnkPlayerName";
			String text = webDriver.findElement(By.id(playerRowId+idSuffix)).getText();
			String[] data = parseStringData(text);
			
			JsonObject player = new JsonObject();
			
			
			player.addProperty("name", data[2]);
			player.addProperty("surname", data[1].substring(0, data[1].length() - 1));
			player.addProperty("playedTime", calculatePlayedTime(data[3]));
			player.addProperty("points", data[4]);
			player.addProperty("two points percentage", calculatePercentage(data[5]));
			player.addProperty("three points percentage", calculatePercentage(data[6]));
			player.addProperty("one points percentage", calculatePercentage(data[7]));
			player.addProperty("rebounds", data[10]);
			player.addProperty("assists", data[11]);
			player.addProperty("steals", data[12]);
			player.addProperty("turnovers", data[13]);
			player.addProperty("blocks", data[14]);
			player.addProperty("personal fouls", data[16]);
			player.addProperty("form index", data[18]);
			
			if(isInStartingLineup(playerRowId+startingLineupIdSuffix)){
				player.addProperty("startingLineup", true);
			} else {
				player.addProperty("startingLineup", false);
			}
			
		    homePlayers.add(player);
		}
		
		return homePlayers;
	}
	
	private String[] parseStringData(String text){
		char[] data = text.toCharArray();
		for(int i = 0; i < data.length; i++){
			if(data[i] == ' ' && data[i+1] == ' '){
				data[i+1] = '/';
			}
		}
		return String.valueOf(data).split(" ");
	}
	
	private boolean isInStartingLineup(String id) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
	    WebElement element = webDriver.findElement(By.id(id));
	    String fontWeight = (String) js.
	            executeScript(
	                    "return getComputedStyle(arguments[0]).getPropertyValue('font-Weight');",
	                    element);
	    if (fontWeight.trim().equals("bold")) {
	       return true;
	    } else {
	       return false;
	    }
	}

	private String calculatePercentage(String string) {
		try{
		    String[] data = string.split("/");
		    double scored = Double.parseDouble(data[0]);
		    double tried = Double.parseDouble(data[1]);
		    double percentage = scored / tried ;
		    return percentage+"";
		} catch(Exception e){
			return "";
		}
	}

	private String calculatePlayedTime(String rawData) {

		try {
			String[] data = rawData.split(":");
			double min = Integer.parseInt(data[0]);
			String sek = "0." + data[1];
			return min + (Double.parseDouble(sek))+"";
		} catch(Exception e) {
			return "";
		}

	}
	
	public JsonObject parseGame(){
		List<WebElement> lw = webDriver.findElements(By.className("score"));
		WebElement first = lw.get(0);
		WebElement second = lw.get(1);
		int home = Integer.parseInt(first.getText());
		int away = Integer.parseInt(second.getText());
		int score = home-away;
		
		JsonObject game = new JsonObject();
		game.addProperty("score", score);
		game.addProperty("home score", home);
		game.addProperty("away score", away);
		game.addProperty("minute", 38.4);
		game.addProperty("ball", true);
		game.addProperty("quality of away team", "excellent");
		game.addProperty("bonus", 0);
		
		return game;

	}
	public void close(){
		webDriver.close();
	}
}
