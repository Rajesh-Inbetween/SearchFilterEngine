package com.cs.ce.delivery.presenter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cs.ce.delivery.response.ResponseCarrierWithStatus;
import com.cs.ce.interactor.incoming.boundary.IPresenter;
import com.cs.ce.interactor.incoming.boundary.IResponseModel;

@Component
public class DefaultPresenter implements IPresenter {
  
  @Override
  public IResponseModel buildFailedResult(Exception exception)
  {
    return new ResponseCarrierWithStatus("FAILURE", exception.getMessage(), exception);
    
  }
  
  @Override
  public IResponseModel buildSuccessResult(Object response)
  {
    return new ResponseCarrierWithStatus("SUCCESS", response, null);
  }
  
  @Override
  public IResponseModel buildSuccessResult(List<Object> response)
  {
    return new ResponseCarrierWithStatus("SUCCESS", response, null);
  }
  
  @Override
  public IResponseModel buildFailedResult(String errorMessage)
  {
    return new ResponseCarrierWithStatus("FAILURE", errorMessage, null);
  }
  
}
