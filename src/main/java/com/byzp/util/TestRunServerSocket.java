package com.byzp.util;

public class TestRunServerSocket {

    public static void main(String[] args){

        final TestServerSocket testServerSocket = new TestServerSocket();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                testServerSocket.runserversocket();
            }
        });

        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                testServerSocket.runserversocket2();
            }
        });

        thread2.start();

    }

}
