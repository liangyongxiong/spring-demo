package com.example.demo.configuration.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableMongoAuditing
@EnableTransactionManagement
@EnableMongoRepositories(
        basePackages = { "com.example.demo.repository.mongo" })
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.mongodb.host}")
    private String host;

    @Value("${spring.mongodb.port}")
    private Integer port;

    @Value("${spring.mongodb.database}")
    private String database;

    @Value("${spring.mongodb.username}")
    private String username;

    @Value("${spring.mongodb.password}")
    private String password;

    @Bean(name = "mongoTransactionManager")
    public MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoClient mongoClient() {
        String uri = null;
        if ("".equals(username) && "".equals(password)) {
            uri = String.format("mongodb://%s:%d/%s", host, port, database);
        } else {
            uri = String.format("mongodb://%s:%s@%s:%d/%s", username, password, host, port, database);
        }
        return new MongoClient(new MongoClientURI(uri));
    }
}
