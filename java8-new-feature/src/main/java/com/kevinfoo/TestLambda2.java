package com.kevinfoo;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一：Lambda表达式的基础语法：Java8中引入类新的操作符"->"
 * 箭头操作服讲Lambda表达式拆分成两部分
 * 左侧：Lambda参数列表
 * 右侧：方法实现
 * 语法格式一：无参数，无返回值
 * () -> System.out.println("kevinfoo")
 * 语法格式二：有一个参数，无返回值
 * 语法格式三：有一个参数的话小括号可以不写
 * 语法格式四：有两个以上的参数，并且有返回值，并且有多条语句
 * 语法格式五：若lambda表达式中只有一条语句，大括号和return可以省略不写
 * 语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文推断出，数据类型，即"类型推断"
 *
 * 上联：左右遇一括号省
 * 下联：左侧推断类型省
 * 横批：能省则省
 *
 * 二：Lambda表达式需要"函数式接口"的支持
 * 函数式接口：只有一个抽象方法的接口，称为函数式接口，可以使用注解@FunctionalIntercace修饰，去检查罢了
 */
public class TestLambda2 {

    int num = 10;

    @Test
    public void test1() {
        // 局部内部变量默认是final
        int num2 = 30;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello, kevinfoo");
            }
        };

        Runnable runnable1 = () -> System.out.println("kevinfoo");

    }

    @Test
    public void test2() {
        //Consumer<String> con = x -> System.out.println(x);
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("kevinfoo");
    }

    public void test3() {
        Comparator<Integer> com = (o1, o2) -> Integer.compare(o1, o2);
    }

    // 需求：对一个数进行运算
    @Test
    public void test4() {
        int cal = cal(20, (x) -> x * x);
        System.out.println("cal = " + cal);
    }

    public int cal(int num, MyFun myFun) {
        return myFun.operation(num);
    }



}
