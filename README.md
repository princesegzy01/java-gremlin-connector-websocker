# Fraud Detection System Architecture
The fraud detection system is a platform that is used to predict and prevent potential fraudulent activities that an adversary can perfrom on a specific platform by taking advantages of a loophole, zero day attack or other cwe's.

The platform is built on the below technologies/stack which has been tested and trusted by big companies to handle and huge volumes of data.

## Technologies/Stack
### 1. Janusgraph
JanusGraph is a scalable graph database optimized for storing and querying graphs containing hundreds of billions of vertices and edges distributed across a multi-machine cluster. 

The janusgraph will serve as the front facing database layer we will be connecting to, to post and get data.


### 2. Cassandra DB
Apache Cassandra is a free and open-source, distributed, wide column store, NoSQL database management system designed to handle large amounts of data across many commodity servers, providing high availability with no single point of failure. 

Cassandra is used by the graph as the underlying database for data storage and retrival.



### 3. Elastic search
Elasticsearch is a search engine based on the Lucene library. It provides a distributed, multitenant-capable full-text search engine with an HTTP web interface and schema-free JSON documents. Elasticsearch is developed in Java

Elastic search is used as the graph indexer to index all the data stored which inturns is used for Full text search with available methods like `textContains()`, `textContainsPrefix()`, `textContainsRegext()`, & `textContainsFuzzy()`,

## Data Schemas
We are enforcing our schema based on [Schema.org](https://schema.org/docs/schemas.html). Schema.org is a collaborative, community activity with a mission to create, maintain, and promote schemas for structured data on the Internet, on web pages, in email messages, and beyond. 


![](https://www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/11-Graph/FIGS/Graphs/graph01.gif)
### Vertex / Entity
Vertex is an obects or model based on a particular type. This can be regards as a single row of data in an RDBMs database table. below is an example of a single vertex.
```
    {
      "@context": "https://schema.org",
      "@type": "Person",
      "address": {
        "@type": "PostalAddress",
        "addressLocality": "Seattle",
        "addressRegion": "WA",
        "postalCode": "98052",
        "streetAddress": "20341 Whitworth Institute 405 N. Whitworth"
     },
    "colleague": [
        "http://www.xyz.edu/students/alicejones.html",
        "http://www.xyz.edu/students/bobsmith.html"
    ],
    "email": "mailto:jane-doe@xyz.edu",
    "image": "janedoe.jpg",
    "jobTitle": "Professor",
    "name": "Jane Doe",
    "telephone": "(425) 123-4567",
    "url": "http://www.janedoe.com"
}
```
### Edges / Relationship
Edges or relationship are the connection between 2 vertices. This somestimes can be regarded as join statement between tables as we can see in the image above.

##Installing
On a single server instance, Make sure you can ssh into the server and open port 8182 to make ingress connection to the graph

1. Download, install and start cassandra database.
2. Download, install and start ElasticSearch
3. Download and extract janusGraph database and cd into it
    - cd into `conf/gremlin-server/` directory
    - edit the `gremlin-server.yaml` file. change the `channelizer` property to `channelizer: org.apache.tinkerpop.gremlin.server.channel.WsAndHttpChannelizer` to use both web socket and Http.
    - To run the application, cd into the root of janusGraph directory and run `./bin/gremlin-server.sh conf/gremlin-server/janusgraph-cql-es-server.properties`
4. Your janusgraph should now run successfully.
5. You can ping the database server on port:8182 to check the connection only if the port has been opened
    