package com.kevinfoo.predicate;

import com.kevinfoo.entity.Employee;

public class AgeFilter implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() > 35;
    }
}
