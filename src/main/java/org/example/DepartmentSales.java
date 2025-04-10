package org.example;

import java.util.*;

public class DepartmentSales {

    public void departmentsalaries() {
        Scanner s = new Scanner(System.in);
        Map<String, List<Double>> departmentSalaries = new HashMap<>();

        System.out.println("Mention the number of departments for salaries:");
        int numDept = Integer.parseInt(s.nextLine());

        for (int i = 0; i < numDept; i++) {
            System.out.println("Enter the department name:");
            String dept = s.nextLine();

            System.out.println("Enter the number of employees in " + dept + ":");
            int numEmp = Integer.parseInt(s.nextLine());

            List<Double> salaries = new ArrayList<>();
            for (int j = 0; j < numEmp; j++) {
                System.out.println("Enter the salary for employee " + (j + 1) + ":");
                double salary = Double.parseDouble(s.nextLine());
                salaries.add(salary);
            }

            departmentSalaries.put(dept, salaries);
        }

        System.out.println(" Average Salaries by Department");
        for (Map.Entry<String, List<Double>> entry : departmentSalaries.entrySet()) {
            String deptName = entry.getKey();
            List<Double> salaries = entry.getValue();

            double total = 0;
            for (double sal : salaries) {
                total += sal;
            }

            double average = salaries.isEmpty() ? 0 : total / salaries.size();
            System.out.println(deptName+" : "+average);
        }
    }
