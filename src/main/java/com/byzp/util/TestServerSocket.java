package com.byzp.util;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class  TestServerSocket{

    /**
     * 服务端
     */
    public void runserversocket() {

        try {

            Scanner sc = new Scanner(System.in);

            System.out.println("服务端已启动，等待客户端连接...");

            while(true){

                    ServerSocket serverSocket = new ServerSocket(10086);

                    System.out.println(serverSocket);

                    Socket socket = serverSocket.accept();

                    InputStream inputStream = socket.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String len = null;

                    String info = "";

                    while ((len = (bufferedReader.readLine())) != null){

                        info += len;
                        System.out.println("Ip地址是： " + socket.getInetAddress().getHostAddress());
                        System.out.println("客户端说：");
                        System.out.println(info);

                    }

                    OutputStream outputStream = socket.getOutputStream();
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    System.out.println("对客户端1说：");
                    printWriter.write(sc.next());

                    printWriter.flush();

                    socket.shutdownOutput();

                    printWriter.close();
                    outputStream.close();
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
                    socket.close();
                    serverSocket.close();

            }

        }catch (Exception e){

            e.printStackTrace();

        }

    }

    /**
     * 服务端2
     */
    public void runserversocket2() {

        try {

            Scanner sc = new Scanner(System.in);

            System.out.println("服务端2已启动，等待客户端连接...");

            while(true){

                ServerSocket serverSocket = new ServerSocket(10087);

                System.out.println(serverSocket);

                Socket socket = serverSocket.accept();

                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String len = null;

                String info = "";

                while ((len = (bufferedReader.readLine())) != null){

                    info += len;
                    System.out.println("Ip地址是： " + socket.getInetAddress().getHostAddress());
                    System.out.println("客户端2说：");
                    System.out.println(info);

                }

                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                System.out.println("对客户端1说：");
                printWriter.write(sc.next());

                printWriter.flush();

                socket.shutdownOutput();

                printWriter.close();
                outputStream.close();
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                socket.close();
                serverSocket.close();

            }

        }catch (Exception e){

            e.printStackTrace();

        }

    }

}
