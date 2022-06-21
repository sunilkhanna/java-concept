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
