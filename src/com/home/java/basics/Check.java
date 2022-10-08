package com.home.java.basics;

import org.apache.spark.sql.catalyst.plans.logical.Except;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Check {

    public static void main(String[] args) {
        Semaphore sp = new Semaphore(3);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        commonCode(sp);
                        System.out.println("Running .....");
                    } catch (Exception e) {

                    }

                }


            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        commonCode(sp);
                        System.out.println("Running .....");
                    } catch (Exception e) {

                    }

                }


            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        commonCode(sp);
                        System.out.println("Running .....");
                    } catch (Exception e) {

                    }

                }


            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        commonCode(sp);
                        System.out.println("Running .....");
                    } catch (Exception e) {

                    }

                }


            }
        }).start();


    }

    public static void commonCode(Semaphore semaphore) throws Exception {
        semaphore.acquire();
        System.out.println("Hello " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (Exception e) {

        }
        semaphore.release();


    }


}
