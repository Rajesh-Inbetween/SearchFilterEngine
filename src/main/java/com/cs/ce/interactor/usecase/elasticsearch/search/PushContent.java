package com.cs.ce.interactor.usecase.elasticsearch.search;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

import com.cs.ce.interactor.incoming.boundary.IInteractor;
import com.cs.ce.interactor.incoming.boundary.IPresenter;

public class PushContent implements IInteractor {
  
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
      
      IndexRequestBuilder indexBuilder = client.prepareIndex(INDEX, DOC_TYPE);
      indexBuilder.setSource(data);
      indexBuilder.execute();
      
      return defaultPresenter.buildSuccessResult(null);
    }
    catch (Exception e) {
      e.printStackTrace();

      return defaultPresenter.buildFailedResult(e);
    }
  }
  
}
