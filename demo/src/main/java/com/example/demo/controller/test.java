package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Person;
import org.springframework.web.bind.annotation.*;

@RestController
public class test {

    /*@RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getId() {
        return "student score is : 59";
    }*/


    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "hello " + name;
    }


    @PostMapping(name ="add",produces = "application/json; charset=UTF-8")
    public String addPerson(@RequestBody Person person){
        return JSON.toJSONString(person);
    }

    @GetMapping("/getPerson/{id}")
    public String getPerson(@PathVariable("id") Integer id){
        return String.join("-",id.toString(),"-name:xiaoming","age:23","sex:女");
    }
}
