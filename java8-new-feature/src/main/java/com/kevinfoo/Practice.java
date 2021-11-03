package com.kevinfoo;

import com.kevinfoo.entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Practice {

    @Test
    public void test1() {
        Employee foo = new Employee("foo", 20, 7667.9);
        Employee wang = new Employee("wang", 29, 6767.8);

        Comparator<Employee> comparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getAge() > o2.getAge()) {
                    return 1;
                } else if (o1.getAge().equals(o2.getAge())) {
                    return o1.getName().compareTo(o2.getName());
                } else {
                    return -1;
                }
            }
        };

        List<Employee> list = Arrays.asList(
                new Employee("foo", 20, 7667.9),
                new Employee("wang", 29, 6767.8));

        Collections.sort(list, comparator);

        list.stream().forEach(System.out::println);
    }

    @Test
    public void test2() {
        List<Employee> list = Arrays.asList(
                new Employee("wang", 29, 6767.8),
        new Employee("foo", 20, 7667.9)
        );

        Collections.sort(list, (o1, o2) -> {
            if (o1.getAge() > o2.getAge()) {
                return 1;
            } else if (o1.getAge().equals(o2.getAge())) {
                return o1.getName().compareTo(o2.getName());
            } else {
                return -1;
            }
        });

        list.stream().forEach(System.out::println);
    }

}
