package org.example.stackoverflow;

import org.apache.commons.lang3.StringUtils;

public class Test3 {

    public static void main(String[] args) {
        stringEquals2();
        String name = " kevin";
        name.replace(" ", "");
        System.out.println("name = " + name);
        //System.out.println("trim = " + trim);
        boolean notBlank = StringUtils.isNotBlank(" ");
        System.out.println("notBlank = " + notBlank);
    }

    static void stringEquals1() {
        String a = "hello";
        String b = "hello";

        System.out.println(a == b);
    }

    static void stringEquals2() {
        String a = "hello";
        String b = new String("hello");

        System.out.println(a.equals(b));
    }

}
