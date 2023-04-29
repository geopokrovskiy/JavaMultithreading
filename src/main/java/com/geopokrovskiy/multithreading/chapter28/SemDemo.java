package com.geopokrovskiy.multithreading.chapter28;

import java.util.concurrent.Semaphore;

class Shared {
    static int count = 0;
}

class IncThread implements Runnable{
    String name;
    Semaphore sem;
    IncThread(Semaphore s, String n){
        sem = s;
        name = n;
        new Thread(this).start();
    }

    @Override
    public void run(){
        System.out.println("Start of the thread " + name);
        try{
            System.out.println("Thread " + name + " is waiting for permission");
            sem.acquire();
            System.out.println("Thread " + name + " is getting the permission");

            for(int i = 0; i < 5; i++){
                Shared.count++;
                System.out.println(name + ": " + Shared.count);

                Thread.sleep(10);
            }
        } catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Thread " + name + " is releasing");
        sem.release();
    }
}

class DecThread implements Runnable{
    String name;
    Semaphore sem;
    DecThread(Semaphore s, String n){
        sem = s;
        name = n;
        new Thread(this).start();
    }

    @Override
    public void run(){
        System.out.println("Start of the thread " + name);
        try{
            System.out.println("Thread " + name + " is waiting for permission");
            sem.acquire();
            System.out.println("Thread " + name + " is getting the permission");

            for(int i = 0; i < 5; i++){
                Shared.count--;
                System.out.println(name + ": " + Shared.count);

                Thread.sleep(10);
            }
        } catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Thread " + name + " is releasing");
        sem.release();
    }
}
public class SemDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        new IncThread(semaphore, "A");
        new DecThread(semaphore, "B");
    }
}
