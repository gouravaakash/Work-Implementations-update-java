package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeList {
    public static List<Employees> getEmplyoee(){
       return Arrays.asList(
               new Employees(1,"Akash","Male","JAVA"),
               new Employees(2,"gaurav","Male","JAVA"),
               new Employees(3,"Anushka","Female","DOT NET"),
               new Employees(4,"Sumit","Male","DOT NET"),
               new Employees(5,"Prachi","Female","PYTHON")
       );
    }
}
