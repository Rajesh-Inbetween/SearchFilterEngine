package com.cs.searchfilter.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.cs.ce.interactor.incoming.boundary.IInteractor;

@ContextConfiguration
public class SearchContentEndsWith extends AbstractTestNGSpringContextTests {

  @Autowired
  IInteractor searchEndsWith;
  
  @Test
  public void execute(){
    searchEndsWith.execute(null);
  }
  
}
