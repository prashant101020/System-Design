package StructuralDesignPattern;
// This is a design pattern where clients can work with both single and groups of elements using the same interface

import java.util.ArrayList;
import java.util.List;

interface orgComponent {
    int getSalary();
    int getHeadCount();
    void printHierarchy(String prefix);
}

class Employee implements orgComponent {
    private final String name;
    private final int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public int getHeadCount() {
        return 1;
    }

    @Override
    public void printHierarchy(String prefix) {
        System.out.println(prefix + name + " (Employee)");
    }
}

class Manager implements orgComponent {
    private final String name;
    private final int salary;
    private final List<orgComponent> subordinates = new ArrayList<>();

    public Manager(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public void addSubordinate(orgComponent subordinate) {
        subordinates.add(subordinate);
    }

    @Override
    public int getSalary() {
        int totalSalary = salary;
        for (orgComponent subordinate : subordinates) {
            totalSalary += subordinate.getSalary();
        }
        return totalSalary;
    }

    @Override
    public int getHeadCount() {
        int count = 1; // Count the manager itself
        for (orgComponent subordinate : subordinates) {
            count += subordinate.getHeadCount();
        }
        return count;
    }

    @Override
    public void printHierarchy(String prefix) {
        System.out.println(prefix + name + " (Manager)");
        for (orgComponent subordinate : subordinates) {
            subordinate.printHierarchy(prefix + "  ");
        }
    }
}
public class Composite {

    public static void main(String[] args) {
        Employee emp1 = new Employee("Alice", 50000);
        Employee emp2 = new Employee("Bob", 60000);
        Manager mgr1 = new Manager("Charlie", 80000);
        mgr1.addSubordinate(emp1);
        mgr1.addSubordinate(emp2);

        Employee emp3 = new Employee("David", 55000);
        Manager mgr2 = new Manager("Eve", 90000);
        mgr2.addSubordinate(emp3);
        mgr2.addSubordinate(mgr1);

        System.out.println("Total Salary under Eve: " + mgr2.getSalary());
        System.out.println("Total Head Count under Eve: " + mgr2.getHeadCount());
        System.out.println("Organization Hierarchy:");
        mgr2.printHierarchy("");
    }
}
