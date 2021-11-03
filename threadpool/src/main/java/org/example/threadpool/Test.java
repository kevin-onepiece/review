package org.example.threadpool;

import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) {
        test1();
    }

    /**
     * corePoolSize:默认线程池大小
     * maximumPoolSize：线程池最大
     * keepAliveTime：当线程池大小大于默认时，多出的这些线程超过keepAliveTime时，会销毁
     * blockQueue：等待队列
     * threadFactory：线程池工厂
     * rejectedExecutionHandler：拒绝策略，超过等待的，警告/?/抛弃被拒绝的/抛弃最老的/new ThreadPoolExecutor.AbortPolicy()/CallerRunsPolicy()/DiscardPolicy()/DiscardOldestPolicy()
     * Executor、Executors、ExecutorService区别？？ThreadPoolExecutor
     */
    public static void test1() {
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " 办理业务");
                    }
            );
        }
        threadPoolExecutor.shutdown();
    }

}
