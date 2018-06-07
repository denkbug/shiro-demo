package com.denk.demo.config;


import com.mongodb.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: denk
 * desc:
 * date: 2017/11/21
 */
@Configuration
public class MongoConfig {

    @Value("${mongo.username}")
    private String username;
    @Value("${mongo.password}")
    private String password;
    @Value("${mongo.databaseName}")
    private String databaseName;
    @Value("#{'${mongo.hosts}'.split(',')}")
    private List<String> hosts;
    @Value("#{'${mongo.ports}'.split(',')}")
    private List<Integer> ports;
    @Value("${mongo.replicaSet}")
    private String replicaSet;

    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory, MongoMappingContext context, CustomConversions conversions) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        mappingConverter.setCustomConversions(conversions);
        // Don't save _class to mongo
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mappingConverter;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(MongoCredential.createScramSha1Credential(username, databaseName, password.toCharArray()));

        List<ServerAddress> serverAddresses = new ArrayList<>();
        for (int i = 0; i < hosts.size(); i++) {
            serverAddresses.add(new ServerAddress(hosts.get(i), ports.get(i)));
        }

        MongoClientOptions options;
        if (StringUtils.isEmpty(replicaSet) && serverAddresses.size() == 1) {
            // 单点连接
            options = MongoClientOptions.builder()
                    .threadsAllowedToBlockForConnectionMultiplier(1500)
                    .socketTimeout(1000 * 60)
                    .build();
        } else {
            // 副本集连接
            options = MongoClientOptions.builder()
                    .requiredReplicaSetName(replicaSet)
                    .threadsAllowedToBlockForConnectionMultiplier(1500)
                    .socketTimeout(1000 * 60)
                    .readPreference(ReadPreference.secondaryPreferred())
                    .build();
        }

        MongoClient mongoClient = new MongoClient(serverAddresses, credentials, options);
        SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(
                mongoClient, databaseName);
        return simpleMongoDbFactory;
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter) throws Exception {
        return new MongoTemplate(mongoDbFactory, mappingMongoConverter);
    }

}
