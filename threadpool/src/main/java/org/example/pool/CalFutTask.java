package org.example.pool;

import java.util.concurrent.*;

/**
 * 2. 使用Callable + FutureTask获取子线程返回结果
 */
public class CalFutTask {
    public static void main(String[] args) {

        CalFutTask calFutTask = new CalFutTask();
        calFutTask.costTime();

    }

    public void test() {
        // 第一种方式
        ExecutorService executorService = Executors.newCachedThreadPool();
        //Future<Integer> result = executorService.submit(new task2());
        Task2 task2 = new Task2();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task2);
        executorService.submit(futureTask);
        // 用来关闭子线程
        executorService.shutdown();

        // 第二种方式 和第一种方式类似，只不过是一个用ExecutorService，一个用的Thread
        Task2 task3 = new Task2();
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(task3);
        Thread thread = new Thread(futureTask1);
        thread.start();

        try {
            System.out.println("运行结果是 = " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void costTime() {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "ms");
    }
}
