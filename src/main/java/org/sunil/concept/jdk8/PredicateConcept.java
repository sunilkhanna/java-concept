package org.sunil.concept.jdk8;

import java.util.function.Predicate;

public class PredicateConcept {

  //Methods-- test, isequals, OR ,negate ,and
  
  
  public static void main(String[] args) {
    
    Predicate<Integer> isgreater=x->(x>9);
    
    System.out.println("is greater "+isgreater.test(90)+" negate "+isgreater.negate().test(90));
    
    System.out.println(new PredicateImplementation().test("KHANNA"));

  }

}
