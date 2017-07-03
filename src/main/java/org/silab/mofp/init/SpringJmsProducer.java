package org.silab.mofp.init;

import org.silab.mofp.businesslogic.sender.MessageSender;
import org.silab.mofp.config.SenderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "org.silab" })
public class SpringJmsProducer {

	@Autowired
	MessageSender sender;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJmsProducer.class, args);
	}
}
