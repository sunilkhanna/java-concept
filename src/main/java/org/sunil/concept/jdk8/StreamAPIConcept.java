package org.sunil.concept.jdk8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.sunil.concept.Employee;

public class StreamAPIConcept {

  public static void main(String[] args) {

    Employee e1 = new Employee("sunil", 10, "asdad adsa");
    Employee e2 = new Employee("asnill", 30, "sda wasdad adsa");
    Employee e3 = new Employee("juhnti", 40, "2wewe asdads we adsa");
    Employee e4 = new Employee("daswse", 50, " trasdad adsa");
    Employee e5 = new Employee("sajnsesew", 60, "nnghs asfd asdad adsa");


    List<Employee> list = new ArrayList<Employee>();
    list.add(e5);
    list.add(e3);
    list.add(e4);
    list.add(e1);
    list.add(e2);

    
    //------print a employee name that begins with 'S' -----//
    
    list.stream().filter(emp -> emp.getName().startsWith("s")).
    forEach(e -> {System.out.println(e.getName());});
    
    
   // list.stream().mapToDouble(e->e.getAge()).forEach(e->{System.out.println(e);});
    
    //----------Sort employee by AGE-------//
    
    list.stream().sorted(new Comparator<Employee>() {public int compare(Employee o1, Employee o2) {
      return 0;};});
    
    list.stream().sorted((o1, o2) -> (o1.getAge() - o2.getAge())).collect(Collectors.toList())
        .forEach(e -> {
          System.out.println(e.getName() + "-" + e.getAge());
        });
    
/**    
    //1st way using comparator....(by this  way processing list itself sorted)
    
    list.sort(new Comparator<Employee>() {

      @Override
      public int compare(Employee o1, Employee o2) {
        
        Integer x1=o1.getAge();
        Integer x2=o2.getAge();
        
        return x1.compareTo(x2);
      }});
    
    for(Employee e:list) {
      
      System.out.println(e.getName()+" "+e.getAge());
      
    }
    
    persons.sort((p1, p2) -> {
        if (p1.getName().compareTo(p2.getName()) == 0) {
            return p1.getAge().compareTo(p2.getAge());
        } else {
            return p1.getName().compareTo(p2.getName());
        } 
    });
    
*/    
    
  //--------stream appraoch---------//
    System.out.println("*****List sorting using Method reference and STREAM****");
  list.stream().sorted(Comparator.comparing(Employee::getName).thenComparing(Employee::getAge))
      .forEach(emp->{System.out.println(emp.getName()+"--"+emp.getAge());});
    
    
    
    
    
    
    
  System.out.println("*********");
    
    
  /*
   * sunil--10 asnill--30 juhnti--40 daswse--50 sajnsesew--60
   */
    
    // -----------get a list of ages---------------------//
    //----------map  and flatten map

    List<Integer> employeeAgeList = list.stream().map(p -> p.getAge()).collect(Collectors.toList());
    System.out.println("No of elements " + employeeAgeList.size());

    // -----------Sorting of employee--------------------//


    // UN-Sorted list
    System.out.println("unsorted list of employee :: ");
    for (Employee e : list) {

      System.out.println(e.getAge());
    }

    Collections.sort(list, new Comparator<Employee>() {
      @Override
      public int compare(Employee o1, Employee o2) {
        return o1.getAge() - o2.getAge();
      }
    });

    // Sorted list
    System.out.println("sorted list of employee :: ");
    for (Employee e : list) {

      System.out.println(e.getAge());
    }


    // ---------Sorting of employee USING Stream API--------------------//
    // ---jdk 8 method reference and chained comparator------//
    Collections.sort(list,
        Comparator.comparing(Employee::getAge).thenComparingInt(Employee::getAge));


  }

}
