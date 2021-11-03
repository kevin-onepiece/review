package com.kevinfoo.predicate;

import com.kevinfoo.entity.Employee;

public class SalaryFilter implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 5005;
    }
}
