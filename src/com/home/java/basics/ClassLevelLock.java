package com.home.java.basics;

import java.util.concurrent.atomic.AtomicInteger;

public class ClassLevelLock implements Runnable{
    static AtomicInteger atomicInteger=new AtomicInteger(0);
    public static void main(String[] args) {

      ClassLevelLock classLevelLock=new ClassLevelLock();
        ClassLevelLock classLevelLock1=new ClassLevelLock();
       Thread t1=new Thread(classLevelLock);
       t1.start();
        atomicInteger.incrementAndGet();
        t1=new Thread(classLevelLock1);
        t1.start();
    }


    @Override
    public void run() {
        if(atomicInteger.get()%2==0) {

            checkLock();
        }
        else
        {
            checkLock1();
        }
    }

    public static synchronized void checkLock()
    {
        System.out.println("Static method....");
        for(int i=0;i<100;i++)
        {
            System.out.println("Print "+Thread.currentThread().getName());
        }


    }
    public  synchronized void checkLock1()
    {
        for(int i=0;i<100;i++)
        {
            System.out.println("Print "+Thread.currentThread().getName());
        }


    }

}
