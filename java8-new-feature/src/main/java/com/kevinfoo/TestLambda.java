package com.kevinfoo;

import com.kevinfoo.entity.Employee;
import com.kevinfoo.predicate.AgeFilter;
import com.kevinfoo.predicate.MyPredicate;
import com.kevinfoo.predicate.SalaryFilter;
import org.junit.Test;

import java.util.*;

public class TestLambda {

    /**
     * 原来的匿名内部类
     */
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    /**
     * Lambda表达式其实就是特殊的匿名内部类
     * Stream API就是对集合的简便操作
     */
    @Test
    public void test2() {
        //Comparator<Integer> comparator = (o1, o2) -> {return  Integer.compare(o1, o2)};
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        TreeSet<Integer> integers = new TreeSet<>(comparator);
    }


    /**
     * 需求：获取当前公司中员工年龄大于35岁的员工信息
     */
    @Test
    public void test3() {
        List<Employee> employees = filterEmployees(this.employees);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9898.9),
            new Employee("李四", 38, 5555.0),
            new Employee("王五", 50, 6666.6),
            new Employee("赵六", 16, 3333.3),
            new Employee("田七", 7, 7777.7)
    );

    /**
     * 获取年龄大于35岁的员工
     * @param list
     * @return
     */
    public List<Employee> filterEmployees(List<Employee> list) {
        ArrayList<Employee> employees = new ArrayList<>();

        for (Employee employee : list) {
            if (employee.getAge() > 35) {
                employees.add(employee);
            }
        }

        return employees;
    }

    /**
     * 获取薪资大于5005的员工信息
     * @param list
     * @return
     */
    public List<Employee> filterEmployees2(List<Employee> list) {
        ArrayList<Employee> employees = new ArrayList<>();

        for (Employee employee : list) {
            if (employee.getSalary() > 5005) {
                employees.add(employee);
            }
        }

        return employees;
    }


    @Test
    public void test4() {
        // 年龄过滤
        List<Employee> employees = filterEmployee(this.employees, new AgeFilter());
        // 薪资过滤
        List<Employee> employees1 = filterEmployee(this.employees, new SalaryFilter());
    }

    /**
     * 优化一：策略设计模式，可是在新加需求时，新加类就好了，不用在之前的类加方法
      */
    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp) {
        ArrayList<Employee> employees = new ArrayList<>();

        for (Employee employee : list) {
            if (mp.test(employee)) {
                employees.add(employee);
            }
        }
        return employees;
    }

    /**
     * 优化二：匿名内部类，不需要加实现类
     */
    public void test5() {
        List<Employee> employees = filterEmployee(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() <= 5000;
            }
        });

        for (Employee employee : employees) {
            System.out.println();
        }
    }

    /**
     * 优化三：Lambda表达式，不需要加MyPredicate接口的实现类
     */
    @Test
    public void test6() {
        List<Employee> employees1 = filterEmployee(this.employees, (employee -> employee.getSalary() <= 5000));
        employees1.forEach(System.out::println);
    }

    /**
     * 优化四：Stream API
     * 不需要MyPredicate接口和它的实现类
     */
    @Test
    public void test7() {
        employees.stream()
                .filter((e)->e.getSalary() > 6000)
                .limit(1)
                .forEach(System.out::println);
    }

}
