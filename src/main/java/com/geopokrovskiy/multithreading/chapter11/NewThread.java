package com.geopokrovskiy.multithreading.chapter11;

public class NewThread implements Runnable{

    Thread t;

    NewThread(){
        t = new Thread(this, "Demo thread");
        System.out.println("The child thread has been created: " + t);
        t.start();
    }

    @Override
    public void run() {
        try{
            for(int i = 5; i > 0 ;i--){
                System.out.println("Child thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e){
            System.out.println("The child thread has been interrupted");
        }
        System.out.println("The child thread has been closed.");
    }
}

class ThreadDemo{
    public static void main(String[] args) {
        new NewThread();
        try{
            for(int i = 5; i > 0; i--){
                System.out.println("Main thread: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e){
            System.out.println("Main thread has been interrupted");
        }
        System.out.println("Main thread has been closed.");
    }
}