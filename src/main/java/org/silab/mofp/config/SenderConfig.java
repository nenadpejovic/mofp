package org.silab.mofp.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.silab.mofp.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class SenderConfig {

	  @Autowired
	  private ConfigurationService config;

	  @Bean
	  @Primary
	  public ActiveMQConnectionFactory activeMQConnectionFactory() {
	    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
	    activeMQConnectionFactory.setBrokerURL(config.getBrokerUrl());
	    activeMQConnectionFactory.setPassword(config.getBrokerPassword());
	    activeMQConnectionFactory.setUserName(config.getBrokerUsername());

	    return activeMQConnectionFactory;
	  }

	  @Bean
	  public CachingConnectionFactory cachingConnectionFactory() {
	    return new CachingConnectionFactory(activeMQConnectionFactory());
	  }

	  @Bean
	  public JmsTemplate jmsTemplate() {
	    return new JmsTemplate(cachingConnectionFactory());
	  }
	  
}
