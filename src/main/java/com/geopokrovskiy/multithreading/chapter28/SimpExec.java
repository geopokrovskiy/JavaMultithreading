package com.geopokrovskiy.multithreading.chapter28;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyThread1 implements Runnable {
    String name;
    CountDownLatch latch;

    MyThread1(CountDownLatch c, String n){
        latch = c;
        name = n;
        new Thread(this);
    }

    @Override
    public void run() {
        for(int i = 0; i < 5 ;i++){
            System.out.println(name +": " + i);
            latch.countDown();
        }
    }
}
public class SimpExec {
    public static void main(String[] args) {
        CountDownLatch cdl1 = new CountDownLatch(5);
        CountDownLatch cdl2 = new CountDownLatch(5);
        CountDownLatch cdl3 = new CountDownLatch(5);
        CountDownLatch cdl4 = new CountDownLatch(5);
        ExecutorService es = Executors.newFixedThreadPool(2);

        System.out.println("Thread launching");

        es.execute(new MyThread1(cdl1, "A"));
        es.execute(new MyThread1(cdl2, "B"));
        es.execute(new MyThread1(cdl3, "C"));
        es.execute(new MyThread1(cdl4, "D"));

        try {
            cdl1.await();
            cdl2.await();
            cdl3.await();
            cdl4.await();
        } catch (InterruptedException e){
            System.out.println(e);
        }

        es.shutdown();
        System.out.println("Thread termination");
    }
}
