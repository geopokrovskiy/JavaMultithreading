package com.geopokrovskiy.multithreading.chapter28;

import java.util.concurrent.CountDownLatch;

class MyThread implements Runnable{

    CountDownLatch latch;
    MyThread(CountDownLatch c){
        latch = c;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            System.out.println(i);
            latch.countDown();
        }
    }
}
public class CDLDemo {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(5);

        System.out.println("Execution thread start");

        new MyThread(cdl);
        try{
            cdl.await();
        } catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Termination of the execution thread");
    }
}
