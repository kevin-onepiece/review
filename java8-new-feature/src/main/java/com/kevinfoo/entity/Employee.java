package com.kevinfoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {

    private String name;
    private Integer age;
    private double salary;

}
