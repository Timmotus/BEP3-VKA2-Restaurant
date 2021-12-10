package com.example.ginos.infrastructure.config;//package nl.bep3_teamtwee.inventory_service.infrastructure.config;
//
//import com.mongodb.client.MongoClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//@Configuration
//public class MongoDbConfig {
//
//    @Autowired
//    MongoClient mongoClient;
//
//    @Value("${spring.data.mongodb.database}")
//    private String database;
//
//    @Bean
//    public MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoClient, database);
//    }
//
//}
