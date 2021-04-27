package com.example.candyorderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.jms.*;
import java.time.LocalTime;

@Service
public class CandyOrderService {

    private final RestTemplate restTemplate;

    private String PLACE_ORDER = "place_order";

    public CandyOrderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void placeOrder()  {
        String url = "http://localhost:8081/place-order";
        ResponseEntity<String> response
                = restTemplate.getForEntity(url , String.class);
        System.out.println(LocalTime.now() +" Place new order with status code " + response.getStatusCode());
    }

    public void placeOrderWithJms() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(PLACE_ORDER);

        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage(LocalTime.now() + " Place new order");

        producer.send(message);
        connection.close();

    }
}
