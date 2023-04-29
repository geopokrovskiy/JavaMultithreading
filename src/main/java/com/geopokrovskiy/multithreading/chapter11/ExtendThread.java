package com.geopokrovskiy.multithreading.chapter11;


class NewThread1 extends Thread {
    NewThread1(){
        super("Demo thread");
        System.out.println("Child thread: " + this);
        start();
    }

    @Override
    public void run(){
        try{
            for(int i = 5; i > 0; i--){
                System.out.println("Child thread: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e){
            System.out.println("The child thread has been interrupted");
        }
        System.out.println("The child thread has been closed.");
    }
}
public class ExtendThread {
    public static void main(String[] args) {
        new NewThread();

        try{
            for(int i = 5; i > 0; i--){
                System.out.println("Main thread: "+ i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e){
            System.out.println("Main thread is interrupted");
        }
        System.out.println("The main thread is closed.");
    }
}
