package com.week2.mongodb.jms;

import com.week2.mongodb.model.Presiden;
import com.week2.mongodb.service.HashmapService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;


@Component
@Slf4j
public class JmsSubcriber {

	@Autowired
	private HashmapService hashmapService;

	@JmsListener(destination = "${mongodb.activemq.topic}")
	public void receiveMessage(final Message jsonMessage){

		if (jsonMessage instanceof TextMessage){
			TextMessage textMessage = (TextMessage) jsonMessage;
			log.info("Get "+ textMessage);

 		}

		hashmapService.reloadHashmap();
		hashmapService.showHashmap();

	}


}
