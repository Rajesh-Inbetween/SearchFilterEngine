package com.cs.ce.interactor.incoming.boundary;

import java.util.List;

public interface IPresenter {
  
  public IResponseModel buildSuccessResult(Object response);
  
  public IResponseModel buildFailedResult(Exception exception);
  
  public IResponseModel buildFailedResult(String errorMessage);
  
  IResponseModel buildSuccessResult(List<Object> response);
  
//  public IResponseModel buildSuccessResult(List<String> response);
  
}
