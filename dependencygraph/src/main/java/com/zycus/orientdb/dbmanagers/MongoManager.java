package com.zycus.orientdb.dbmanagers;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;

/**
 * Created by megha.ray on 8/28/2017.
 */
public class MongoManager {
//    private static final Logger log = Logger.getLogger(MongoManager.class);

    static MongoClient getMongoClient(String host){

        MongoClient mongoClient = new MongoClient(host);
        return mongoClient;
    }

    private static void getSwarmData(){
        MongoClient swarmClient = getMongoClient("localhost");
        swarmClient.getDatabase("bee_hive");
    }

    public static MongoCollection<Document> getTiftixData(){
        MongoClient tiftixClient = getMongoClient("192.168.4.100");
        MongoDatabase tiftixDB = tiftixClient.getDatabase("processagent");
        MongoCollection<Document> tiftixColl = tiftixDB.getCollection("process_live");
        return tiftixColl;
    }

}
