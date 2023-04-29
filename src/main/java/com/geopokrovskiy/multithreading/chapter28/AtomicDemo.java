package com.geopokrovskiy.multithreading.chapter28;

import java.util.concurrent.atomic.AtomicInteger;

class Shared2{
    static AtomicInteger ai = new AtomicInteger(0);
}

class AtomThread implements Runnable{
    String name;
    AtomThread(String n){
        name = n;
        new Thread(this).start();
    }
    @Override
    public void run() {
        System.out.println("Thread start: " + name);
        for(int i = 1; i <= 3; i++){
            System.out.println("Thread " + name + " received: "+
                    Shared2.ai.getAndSet(i));
        }
    }
}
public class AtomicDemo {
    public static void main(String[] args) {
        new AtomThread("A");
        new AtomThread("B");
        new AtomThread("C");
    }
}
