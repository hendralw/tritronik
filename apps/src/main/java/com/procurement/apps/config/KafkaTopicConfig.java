package com.procurement.apps.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic getUserTopic(){
        return TopicBuilder.name("getUser").build();
    }

    @Bean
    public NewTopic helloTopic(){
        return TopicBuilder.name("hello").build();
    }
}
