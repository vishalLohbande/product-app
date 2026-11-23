package com.example.perfumeShop.KafkaConfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.requests.OffsetCommitResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KagkaConfig {

    @Bean
    public NewTopic getNewTopic(){
        return TopicBuilder
                .name("product-event")
                .replicas(1)
                .partitions(1)
                .build();
    }



}
