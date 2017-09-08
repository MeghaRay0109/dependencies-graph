import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

import java.io.IOException;

/**
 * Created by megha.ray on 8/31/2017.
 */
public class Test {
    public static void main(String args[])throws IOException {
//        ODatabaseDocumentTx db = new ODatabaseDocumentTx ("plocal:D:/orientdb-2.2.25/databases/testing2");
//        db.create();
////        db.open("root","root");
//
//        ODocument product = new ODocument("Product");
//        product.field( "name", "eProc" );
//        product.field( "ip", "10.2.2.5" );
//        product.field("env", "Production");
//        product.field("jboss_path","/U01/prodeproc/integration-node1/application/something/apache-tomcat-7.0.22");
//        product.save();

        OrientGraphFactory factory = new OrientGraphFactory("plocal:D:/orientdb-2.2.25/databases/testing2").setupPool(1,10);
        OrientGraph graph = factory.getTx();
        try {
            OrientVertex environment = graph.addVertex(null);
            environment.setProperty("env", "pdt-rm");
            OrientVertex host = graph.addVertex(null);
            host.setProperty("ip", "10.2.2.5");
            Edge envHasHost = graph.addEdge(null, environment, host, "has");
            graph.commit();
        } catch( Exception e ) {
            graph.rollback();
        }
        finally {
            graph.shutdown();
        }
    }
}
