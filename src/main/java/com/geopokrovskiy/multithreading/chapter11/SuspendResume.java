package com.geopokrovskiy.multithreading.chapter11;

class NewThread4 implements Runnable{

    String name;
    Thread t;
    boolean suspendFlag;

    NewThread4(String threadName){
        name = threadName;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        suspendFlag = false;
        t.start();
    }

    @Override
    public void run() {
        try {
            for(int i = 15; i >0; i--){
                System.out.println(name + ": " + i);
                Thread.sleep(200);
                synchronized (this){
                    while(suspendFlag){
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + " is interrupted");
        }
        System.out.println(name + " is closed.");
    }

    synchronized void mysuspend(){
        suspendFlag = true;
    }

    synchronized void myresume(){
        suspendFlag = false;
        notify();
    }
}
public class SuspendResume {




    public static void main(String[] args) {
        NewThread4 ob1 = new NewThread4("One");
        NewThread4 ob2 = new NewThread4("Two");
        try{
            Thread.sleep(1000);
            ob1.mysuspend();
            System.out.println("One thread is suspended");
            Thread.sleep(1000);
            ob1.myresume();
            System.out.println("One thread is resumed");
            ob2.mysuspend();
            System.out.println("Two thread is suspended");
            Thread.sleep(1000);
            ob2.myresume();
            System.out.println("Two thread is resumed");
        } catch(InterruptedException e){
            System.out.println("Main thread is interrupted");
        }

        try{
            System.out.println("Waiting for the treads to be terminated");
            ob1.t.join();
            ob2.t.join();
        } catch (InterruptedException e){
            System.out.println("Main thread is interrupted");
        }
        System.out.println("Main thread is closed");
    }
}
