package org.silab.mofp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

@Service
@PropertySources({
	@PropertySource("classpath:activemq.properties"),
	@PropertySource("classpath:scraper.properties")
})
public class ConfigurationService {

	  @Value("${activemq.broker-url}")
	  private String brokerUrl;
	  
	  @Value("${activemq.broker-username}")
	  private String brokerUsername;
	  
	  @Value("${activemq.broker-password}")
	  private String brokerPassword;
	  
	  @Value("${driver.chromedriver}")
	  private String chromeDriver;
	  
	  
	  @Value("${homeplayer.row.id}")
	  private String homePlayerRowId;
	  
	  @Value("${awayplayer.row.id}")
	  private String awayPlayerRowId;

	  @Value("${match.url}")
	  private String matchUrl;
	  
	  @Value("${activemq.broker-queue}")
	  private String queue;
	  
	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getAwayPlayerRowId() {
		return awayPlayerRowId;
	}

	public void setAwayPlayerRowId(String awayPlayerRowId) {
		this.awayPlayerRowId = awayPlayerRowId;
	}

	public String getMatchUrl() {
		return matchUrl;
	}

	public void setMatchUrl(String matchUrl) {
		this.matchUrl = matchUrl;
	}

	public String getHomePlayerRowId() {
		return homePlayerRowId;
	}

	public void setHomePlayerRowId(String homePlayerRowId) {
		this.homePlayerRowId = homePlayerRowId;
	}

	public String getBrokerUrl() {
		return brokerUrl;
	}

	public void setBrokerUrl(String brokerUrl) {
		this.brokerUrl = brokerUrl;
	}

	public String getBrokerUsername() {
		return brokerUsername;
	}

	public void setBrokerUsername(String brokerUsername) {
		this.brokerUsername = brokerUsername;
	}

	public String getBrokerPassword() {
		return brokerPassword;
	}

	public void setBrokerPassword(String brokerPassword) {
		this.brokerPassword = brokerPassword;
	}

	public String getChromeDriver() {
		return chromeDriver;
	}

	public void setChromeDriver(String chromeDriver) {
		this.chromeDriver = chromeDriver;
	}
  
}
