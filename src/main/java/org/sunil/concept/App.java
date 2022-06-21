package org.sunil.concept;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
 
public class App {
  public static void main(String[] args) {
  
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    numbers.add(5);
    numbers.add(9);
    numbers.add(8);
    numbers.add(1);
    numbers.forEach( (n) -> { System.out.println(n); } );
    String a="s";
    GreetMe isgreeted=(s)->{ System.out.println("asdasd"+s);};
    
    sum(1, 1);
  
    Calculation c=(n,b) -> {System.out.println("cal :: "+(n*b));};
    
    c.mul(4, 4);
    System.out.println("sdd ");
  }
  
  private static void sum(int a, int b) {
    
    System.out.println("sum is :: "+ (a+b));
    
  }
  
  
  interface MathOperation {
    int operation(int a, int b);
 }
  interface GreetMe {
    void greetMe(String name);
 }
  interface Calculation{
    
    //void sum(int a, int b);
    void mul(int c, int v);
  }
  
}
