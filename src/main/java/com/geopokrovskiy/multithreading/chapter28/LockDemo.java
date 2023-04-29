package com.geopokrovskiy.multithreading.chapter28;

import java.util.concurrent.locks.ReentrantLock;

class Shared1{
    static int count = 0;
}

class LockThread implements Runnable{
    String name;
    ReentrantLock lock;
    LockThread(ReentrantLock lk, String n){
        lock = lk;
        name = n;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Thread start: " + name);

        try{
            System.out.println("Thread " + name + " is waiting for counter blocking");
            lock.lock();
            System.out.println("Thread " + name + " is blocking the counter");
            Shared1.count++;
            System.out.println("Thread " + name + ": " + Shared1.count);
            System.out.println("Thread " + name + " is waiting");
            Thread.sleep(1000);
        } catch (InterruptedException e){
            System.out.println(e);
        } finally {
            System.out.println("Thread " + name  + " is unlocking the counter");
            lock.unlock();
        }
    }
}
public class LockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new LockThread(lock, "A");
        new LockThread(lock, "B");
    }
}
