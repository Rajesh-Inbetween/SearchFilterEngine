package com.cs.searchfilter.elasticsearch;

import javax.annotation.PostConstruct;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Elasticsearch {
  
  private Node node;

  private Client client;
  
  @PostConstruct
  public void startup()
  {
    node = NodeBuilder.nodeBuilder().node();
    client = node.client();
  }
  
  @Bean
  public Client client(){
    return client;
  }
  
  
}
