package com.zycus.orientdb.dbmanagers;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

/**
 * Created by megha.ray on 8/31/2017.
 */
public class OrientGraphManager {
    static OrientGraphFactory factory = new OrientGraphFactory("plocal:D:/orientdb-2.2.25/databases/pdtrm-POC").setupPool(1,10);
    
    public static OrientGraph getGraph(){
        OrientGraph graph = factory.getTx();
        return graph;
    }
}
