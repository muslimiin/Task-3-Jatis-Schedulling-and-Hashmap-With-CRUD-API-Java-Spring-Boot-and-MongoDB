package com.week2.mongodb.jms;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
@Slf4j
public class JmsProducer {

    @Value("${mongodb.activemq.topic}")
    String topic;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final String message){
        log.info("Send Message "+ message);
        jmsTemplate.send(topic, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage();
                msg.setText(message);
                return msg;
            }
        });
    }


}
