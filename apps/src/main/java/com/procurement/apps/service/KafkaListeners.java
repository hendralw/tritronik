package com.procurement.apps.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListeners {
    @KafkaListener(topics = "hello")
    public void subscribeHello(String data){
        System.out.println("[hello] received message: \" + data");
    }
}
