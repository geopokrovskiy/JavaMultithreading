package com.geopokrovskiy.multithreading.chapter11;

class NewThread2 implements Runnable{
    String name;
    Thread t;

    NewThread2(String threadName){
        name = threadName;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        t.start();
    }

    @Override
    public void run() {
        try{
            for(int i = 5; i > 0; i--){
                System.out.println(name + ": " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e){
            System.out.println(name + " is interrupted");
        }
        System.out.println(name + " is closed.");
    }
}
public class MultiThreadDemo {
    public static void main(String[] args) {
        new NewThread2("One");
        new NewThread2("Two");
        new NewThread2("Three");

        try{
            // waiting for other threads
            Thread.sleep(10000);
        } catch (InterruptedException e){
            System.out.println("The main thread has been interrupted");
        }
        System.out.println("The main thread is closed.");
    }
}
