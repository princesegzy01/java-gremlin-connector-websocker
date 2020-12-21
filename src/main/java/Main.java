package main.java;


import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;

import java.io.File;
import java.io.FileNotFoundException;
import org.apache.tinkerpop.gremlin.driver.ser.Serializers;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.T;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Main {


    private  static  GraphTraversalSource g;
    private static  DriverRemoteConnection connection;

//    http://95.216.192.194:8182/
//    GraphTraversalSource g = traversal().withRemote('conf/remote-graph.properties');
    public static void main (String args[]){
        System.out.println("This is good");


        try{
//            DriverRemoteConnection connection = DriverRemoteConnection.using(client);
            connection = DriverRemoteConnection.using("95.216.192.194", 8182);
            g = AnonymousTraversalSource.traversal().withRemote(connection);


//            Vertex v1 = g.addV("Car").property("name", "Bola").property("age", 18).next();

            // Execute query
//            List<Object> results = g.V().values("name", "age").toList();
            List<Map<Object, Object>> results = g.V().elementMap().toList();
//            List<Object> results = g.V().limit(10).values("name").toList();
            System.out.println(results.toString());
        } finally {
            // Close client - this closes client connections
//            client.close();
        }

    }

    // Get all vertices by a propery and value
    public List GetAllVerticesByProperty(String property, String value){
        List<Map<Object, Object>> results = g.V().has(property, value).elementMap().toList();
        return results;
    }

    // Get all vertices by Label
    public List GetVerticesByLabel(String label){
        List<Map<Object, Object>> results = g.V(label).elementMap().toList();
        return results;
    }

    // Insert data into the graph
    public void Insert(){
        // Create a HashMap object called capitalCities
        HashMap<String, String> data = new HashMap<String, String>();

        // Add keys and values (Country, City)
        data.put("label", "Country");
        data.put("England", "London");
        data.put("Germany", "Berlin");
        data.put("Norway", "Oslo");
        data.put("USA", "Washington DC");

        GraphTraversal<Vertex,Vertex> t = g.addV(data.get(data.get("label")));
        data.forEach((k,v) -> t.property(k,v));
        Vertex v = t.next();

    }
}
