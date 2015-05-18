package com.cs.ce.delivery.response;

import com.cs.ce.interactor.incoming.boundary.IResponseModel;

public class ResponseCarrierWithStatus implements IResponseModel {
  
  Object response;
  String status;
  Object additionalInfo;
  
  public ResponseCarrierWithStatus(String status, Object response, Object additionalInfo)
  {
    this.status = status;
    this.response = response;
    this.additionalInfo = additionalInfo;
  }
  
  public Object getResponse()
  {
    return response;
  }
  
  public void setResponse(Object response)
  {
    this.response = response;
  }
  
  public String getStatus()
  {
    return status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Object getAdditionalInfo()
  {
    return additionalInfo;
  }
  
  public void setAdditionalInfo(Object additionalInfo)
  {
    this.additionalInfo = additionalInfo;
  }

  @Override
  public String toString() {
    return "ResponseCarrierWithStatus{" +
            "response=" + response +
            ", status='" + status + '\'' +
            ", additionalInfo=" + additionalInfo +
            '}';
  }
}
