package org.example.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用线程池
 */
public class Main {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 14; i++) {
            Future<?> submit = executorService.submit(new task("" + i));
        }
        executorService.shutdown();

    }

}

class task implements Runnable{

    private final String name;

    public task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("start task " + name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println("end task " + name);
    }
}
