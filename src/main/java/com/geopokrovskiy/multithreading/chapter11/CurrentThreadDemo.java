package com.geopokrovskiy.multithreading.chapter11;

public class CurrentThreadDemo {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("Current thread: " + t);
        t.setName("MyThread");
        System.out.println("After thread's name update: " + t);

        try{
            for(int n = 5; n > 0; n--){
                System.out.println(n);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e){
            System.out.println("The main thread has been interrupted.");
        }
    }
}
