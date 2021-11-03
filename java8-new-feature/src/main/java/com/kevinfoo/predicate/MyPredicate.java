package com.kevinfoo.predicate;

@FunctionalInterface
public interface MyPredicate<T> {

    boolean test(T t);

}
