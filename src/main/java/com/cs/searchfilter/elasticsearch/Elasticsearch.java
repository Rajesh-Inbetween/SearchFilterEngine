package com.cs.searchfilter.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;


public class Elasticsearch {
  
  private Node node;

  private Client client;
  
  private static Elasticsearch instance;
  
  static {
    instance = new Elasticsearch();
  }
  
  private Elasticsearch()
  {
    node = NodeBuilder.nodeBuilder().node();
    client = node.client();
  }
  
  public static Client getClient(){
    return instance.getClient();
  }
  
  
}
