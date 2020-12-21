package main.java;

import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Service {

    public     GraphTraversalSource G;
    public    DriverRemoteConnection Connection;

    Service(String host, int port){
        DriverRemoteConnection connection = DriverRemoteConnection.using(host, port);
        GraphTraversalSource g = AnonymousTraversalSource.traversal().withRemote(connection);

        this.G = g;
        this.Connection = connection;
    }

    // Add transaction Add fraudulent
    // transaction to the graph
    public Vertex AddTransaction(HashMap data){
        GraphTraversal<Vertex,Vertex> t = G.addV( (String) data.get("_label"));
        data.forEach((k,v) -> t.property(k,v));
        Vertex v = t.next();
        return v;

    }


    // Get all vertices by a propery and value
    public List GetAllVerticesByProperty(String property, String value){
        List<Map<Object, Object>> results = G.V().has(property, value).elementMap().toList();
        return results;
    }

    // Get all vertices
    public List GetAllVertices(){
        List<Map<Object, Object>>  results = G.V().elementMap().toList();
        return results;
    }


    // Get all vertices by Label
    public List GetVerticesByLabel(String label){
        List<Map<Object, Object>> results = G.V(label).elementMap().toList();
        return results;
    }
}
