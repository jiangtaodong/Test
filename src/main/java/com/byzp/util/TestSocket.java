package com.byzp.util;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TestSocket {

    public static void main(String[] args){

        runsocket();

    }

    /**
     * 客户端
     */
    public static void runsocket() {

        try {

            Scanner sc = new Scanner(System.in);

            System.out.println("客户端已启动，正在连接服务器...");

            while(true){

                Socket socket = new Socket("localhost",10086);

                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);

                System.out.println("请输入：");

                printWriter.write(sc.next());

                printWriter.flush();

                socket.shutdownOutput();

                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String len = null;
                String info = "";

                while ((len = (bufferedReader.readLine())) != null){

                    info += len;
                    System.out.println("Ip地址是： " + socket.getInetAddress().getHostName());
                    System.out.println("服务端说：");
                    System.out.println(info);

                }

                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                printWriter.close();
                outputStream.close();
                socket.close();

            }

        }catch (Exception e){

            e.printStackTrace();

        }

    }

}
