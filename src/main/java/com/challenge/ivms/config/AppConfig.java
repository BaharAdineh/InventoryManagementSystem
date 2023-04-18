package com.challenge.ivms.config;

import com.mongodb.MongoSocketOpenException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;

@slf4j
@Configuration
@EnableMongoRepositories(basePackages = "com.challenge.ivms.repository")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Override
    public MongoClient mongoClient() {
        MongoClient mongoClient = null;
        try {
            mongoClient = MongoClients.create(mongoUri);
        } catch (MongoSocketOpenException e) {
            // Handle the exception
            log.error("Exception opening socket: " + e.getMessage());
        }
        return mongoClient;
    }

    @Override
    protected String getDatabaseName() {
        return "mydatabase";
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}