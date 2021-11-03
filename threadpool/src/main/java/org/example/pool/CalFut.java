package org.example.pool;

import java.util.concurrent.*;

/**
 * 1. 使用Callable + Future获取子线程返回结果
 */
public class CalFut {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> result = executorService.submit(new Task2());
        executorService.shutdown();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程在执行");

        try {
            System.out.println("运行结果 = " + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("over");
    }

}

class Task2 implements Callable {

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在执行");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }
        return sum;
    }
}
