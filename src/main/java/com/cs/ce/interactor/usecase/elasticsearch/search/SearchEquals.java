package com.cs.ce.interactor.usecase.elasticsearch.search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;

import com.cs.ce.interactor.incoming.boundary.IInteractor;
import com.cs.ce.interactor.incoming.boundary.IPresenter;

public class SearchEquals implements IInteractor {
  
  private static String INDEX    = "";
  
  private static String DOC_TYPE = "";
  
  @Autowired
  protected Client      client;
  
  @Autowired
  IPresenter            defaultPresenter;
  
  @Override
  public Object execute(Object data)
  {
    try {
      Map<String, String> searchParameters = (Map<String, String>) data;
      
      String fieldName = searchParameters.get("field");
      String fieldValueEquals = searchParameters.get("value");
      
      SearchRequestBuilder searchBuilder = client.prepareSearch(INDEX);
      MatchQueryBuilder matchQuery = QueryBuilders.matchQuery(fieldName, fieldValueEquals);
      searchBuilder.setTypes(DOC_TYPE);
      searchBuilder.setSearchType(SearchType.QUERY_AND_FETCH);
      searchBuilder.setQuery(matchQuery);
      
      SearchResponse response = searchBuilder.execute().actionGet();
      SearchHit[] results = response.getHits().getHits();
      
      Set<Map<String, Object>> resultSet = new HashSet<Map<String, Object>>();
      for (SearchHit hit : results) {
        Map<String, Object> source = hit.getSource();
        resultSet.add(source);
      }
      
      return defaultPresenter.buildSuccessResult(resultSet);
      
    }
    catch (Exception e) {
      e.printStackTrace();
      
      return defaultPresenter.buildFailedResult(e);
    }
  }
  
}
