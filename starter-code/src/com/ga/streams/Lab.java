package com.ga.streams;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lab {

    private List<Employee> employees = Arrays.asList(
            new Employee("Bezos, Jeff", LocalDate.of(2004, 4, 2), 68_109.00, "Male"),
            new Employee("Sheryl Sandberg", LocalDate.of(2014, 7, 1), 87_846.00,"Female"),
            new Employee("Buffet, Warren", LocalDate.of(2011, 7, 23), 95_035.00, "Male"),
            new Employee("Susan Wojcick", LocalDate.of(2015, 6, 1), 37_210.00, "Female"),
            new Employee("Zuckerberg, Mark", LocalDate.of(2016, 5, 12), 48_450.00, "Male"),
            new Employee("Brin, Sergey", LocalDate.of(2016, 8, 5), 74_416.00, "Male")
    );

    public static void main(String[] args) {
        Lab lab = new Lab();
    }

    private <T> void printList(List<T> list) {
        list.forEach( item -> System.out.println(item));
    }

    @Test
    public void getEmployeesOver50k() {
//        List<Employee> employees = null;
        List<Employee> employeesOver50k = employees.stream()
                .filter(employee -> employee.getSalary() >= 50_000)
                .collect(Collectors.toList());
        printList(employeesOver50k);
    }

    @Test
    public void getEmployeeNamesHiredAfter2012() {
//        List<String> employees = null;
        List<String> employeesAfterDate = employees.stream()
                .filter(employee -> employee.getHireDate().isBefore(LocalDate.of(2012, 1, 1)))
                .map(employee -> employee.getName())
                .collect(Collectors.toList());
        printList(employeesAfterDate);
    }

    @Test
    public void getMaxSalary() {
//        double max = 0;
        double max = employees.stream()
                .sorted((emp1, emp2) -> emp1.getSalary().compareTo(emp2.getSalary()))
                .mapToDouble(emp -> emp.getSalary())
                .max()
                .orElse(0);
        System.out.println("Max:" + max);

    }

    @Test
    public void getMinSalary() {
        double min = employees.stream()
                .sorted((emp1, emp2) -> emp1.getSalary().compareTo(emp2.getSalary()))
                .mapToDouble(emp -> emp.getSalary())
                .min()
                .orElse(0);
        System.out.println("Min:" + min);
    }

    @Test
    public void getAverageSalaries() {
        double averageMale = employees.stream()
                .filter(emp -> emp.getGender()=="Male")
                .mapToDouble(emp -> emp.getSalary())
                .average()
                .orElse(0);
        double averageFemale = employees.stream()
                .filter(emp -> emp.getGender()=="Female")
                .mapToDouble(emp -> emp.getSalary())
                .average()
                .orElse(0);

        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
    }

    @Test
    public void getMaximumPaidEmployee() {
        Employee highest = employees.stream()
                .sorted((emp1, emp2) -> emp2.getSalary().compareTo(emp1.getSalary()))
                .findFirst()
                .orElse(null);
        System.out.println(highest);
    }
}

