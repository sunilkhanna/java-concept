package org.sunil.concept.jdk8;

import java.util.function.Predicate;

public class PredicateImplementation implements Predicate<String> {

  @Override
  public boolean test(String s) {
    
    return s.equals("test");
  }

}
