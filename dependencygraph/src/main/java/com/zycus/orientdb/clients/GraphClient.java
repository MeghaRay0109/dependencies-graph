package com.zycus.orientdb.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.zycus.orientdb.dbmanagers.OrientGraphManager;
import com.zycus.orientdb.entities.Process;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by megha.ray on 8/31/2017.
 */
public class GraphClient {
    static OrientGraph graph = OrientGraphManager.getGraph();
    static List<OrientVertex> env = new ArrayList();
    static List<OrientVertex> hosts = new ArrayList();
    List procs = new ArrayList();

    public static void client(MongoCollection tiftixColl){
        // ---------       Creating Vertices        -----------
        DistinctIterable<String> envItr = tiftixColl.distinct("procInfo.env", String.class);
        DistinctIterable<String> hostItr = tiftixColl.distinct("procInfo.address", String.class);
        try{
            for(String e : envItr){
                createVertex("class:Environment","env",e);
            }
            for(String h : hostItr){
                createVertex("class:Host","host",h);
//                addresses.add(h);
            }

        //--------        Creating Host-Env Edges       ---------
            for (OrientVertex h : hosts){
                createEdge(env.get(0),h,"HasHost");
            }

        // ---------       Creating Proc-Host Edges     ---------
        FindIterable itr = tiftixColl.find();
        MongoCursor<Document> cursor = itr.iterator();
        while (cursor.hasNext()){
            OrientVertex v = null;
            Document doc = cursor.next();
            Document procInfo = (Document) doc.get("procInfo");
                for(OrientVertex ip : hosts ){
                    if(procInfo.containsValue(ip.getProperty("host"))){
                        String proc = (String) doc.get("cname");
                        System.out.println("IP : " + ip + " Proc: " +proc);
                        v = createVertex("class:Process", "cname", proc);
                        ObjectMapper mapper = new ObjectMapper();
                        String json = doc.toJson();
                        Process procData = mapper.readValue(json, Process.class);
                        System.out.println(procData.getCname());
//                        v.setProperties(procData);
                        createEdge(ip,v,"RunsProcess");
                    }
                }

            }
        graph.commit();
        } catch( Exception e ) {
            System.out.println(e);
            graph.rollback();
        } finally {
            graph.shutdown();
        }
    }

    static OrientVertex createVertex(String id, String vName, String vValue){
        OrientVertex v = graph.addVertex(id);
        v.setProperty(vName,vValue);
        if(vName.equalsIgnoreCase("env")){
            env.add(v);
        } else if (vName.equalsIgnoreCase("host")){
            hosts.add(v);
        } else if (vName.equalsIgnoreCase("cname")){
            return v;
        }
        return v;
    }

    static void createEdge(OrientVertex node1, OrientVertex node2, String label){
        System.out.println(node1.toString()+"---------"+node2.toString());
        OrientEdge e = graph.addEdge(null, node1, node2, label);
    }

    static void addProperties(OrientVertex v , Document doc){

    }


}
