package com.geopokrovskiy.multithreading.chapter11;

class A{
    synchronized void foo(B b){
        String name = Thread.currentThread().getName();
        System.out.println(name+ " has entered the A.foo() method");

        try{
            Thread.sleep(1000);
        } catch (Exception e){
            System.out.println("class A is interrupted");
        }
        System.out.println(name + " is trying to call the B.last() method");
        b.last();
    }

    synchronized void last(){
        System.out.println("In the method A.last()");
    }
}

class B{
    synchronized void bar(A a){
        String name = Thread.currentThread().getName();
        System.out.println(name+ " has entered the B.bar() method");

        try{
            Thread.sleep(1000);
        } catch (Exception e){
            System.out.println("class B is interrupted");
        }
        System.out.println(name + " is trying to call the A.last() method");
        a.last();
    }

    synchronized void last(){
        System.out.println("In the method A.last()");
    }
}

public class Deadlock implements Runnable{
    A a = new A();
    B b = new B();

    Deadlock(){
        Thread.currentThread().setName("Main thread");
        Thread t = new Thread(this, "Concurrent thread");
        t.start();
        a.foo(b);
        System.out.println("Back to the main thread");
    }

    @Override
    public void run() {
        b.bar(a); // получить блокировку для объекта b в другом потоке
        System.out.println("back to the other thread");
    }

    public static void main(String[] args) {
        new Deadlock();
    }
}
