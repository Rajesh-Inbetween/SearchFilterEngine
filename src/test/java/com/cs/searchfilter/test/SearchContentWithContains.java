package com.cs.searchfilter.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.cs.ce.interactor.incoming.boundary.IInteractor;

@ContextConfiguration
public class SearchContentWithContains extends AbstractTestNGSpringContextTests  {

  @Autowired
  IInteractor searchContains;
  
  @Test
  public void execute(){
    searchContains.execute(null);
  }
  
}
