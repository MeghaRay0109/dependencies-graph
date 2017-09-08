package com.zycus.orientdb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.zycus.orientdb.clients.GraphClient;
import com.zycus.orientdb.dbmanagers.MongoManager;
import org.bson.Document;

import java.io.IOException;
import java.util.Map;

/**
 * Created by megha.ray on 8/31/2017.
 */
public class Main {
    public static void main(String args[]) throws IOException {

        MongoCollection<Document> coll = MongoManager.getTiftixData();
        GraphClient.client(coll);
    }
}
