package com.geopokrovskiy.multithreading.chapter11;

class NewThread3 implements Runnable{
    String name;
    Thread t;
    NewThread3(String threadName){
        name = threadName;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        t.start();
    }

    @Override
    public void run() {
        try {
            for(int i = 5; i > 0; i--){
                System.out.println(name + ": " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e){
            System.out.println(name + " is interrupted.");
        }
        System.out.println(name + " is closed.");
    }
}
public class DemoJoin {
    public static void main(String[] args) {
        NewThread3 ob1 = new NewThread3("One");
        NewThread3 ob2 = new NewThread3("Two");
        NewThread3 ob3 = new NewThread3("Three");

        System.out.println("Stream One has started: " + ob1.t.isAlive());
        System.out.println("Stream Two has started: " + ob2.t.isAlive());
        System.out.println("Stream Three has started: " + ob3.t.isAlive());

        try{
            System.out.println("Waiting for the threads: ");
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        } catch (InterruptedException e){
            System.out.println("The main thread is interrupted.");
        }
        System.out.println("Stream One has started: " + ob1.t.isAlive());
        System.out.println("Stream Two has started: " + ob2.t.isAlive());
        System.out.println("Stream Three has started: " + ob3.t.isAlive());
        System.out.println("The main thread is closed.");
    }
}
