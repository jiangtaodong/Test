package com.byzp.util;

public class TestThread implements Runnable{

    @Override
    public synchronized void run() {//被synchronized使用的方法不能调用

        /*synchronized (this){*/ //允许访问控制的代码

            for(int i = 0; i < 100; i++){

                System.out.println(Thread.currentThread().getName() + " ******Thread****** " + "【" + i + "】");

            }

       /* }*/

    }

    public static void main(String[] args){

        TestThread testThread = new TestThread();

        System.out.println(" *************************** TestThread ****************************** ");

        Thread thread1 = new Thread(testThread,"A");

        Thread thread2 = new Thread(testThread,"B");

        thread1.start();

        thread2.start();

    }

}
