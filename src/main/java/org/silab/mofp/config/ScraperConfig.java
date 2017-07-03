package org.silab.mofp.config;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.JsonObject;

@Configuration
public class ScraperConfig {
	
	@Autowired
	ConfigurationService config;

	@Bean
	public WebDriver getDriver(){
		File file = new File(config.getChromeDriver());
	    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		WebDriver chromeDriver = new ChromeDriver();
		return chromeDriver;
	}
	
	@Bean
	public JsonObject getMessage(){
		return new JsonObject();
	}

}
