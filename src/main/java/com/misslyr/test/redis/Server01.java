package com.misslyr.test.redis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author missli
 * @Description 黑客技术，编写一个socket接受TCP连接
 * @Date 2019/6/14 16:45
 **/
public class Server01 {
    public static void main(String[] args) {
        Socket socket= null;
        ServerSocket serverSocket = null;
        InputStream inputStream = null;
        try {
            serverSocket = new ServerSocket(9999);
            System.out.println("等待socket连接");  ////请求到来则产生一个Socket对象，并继续执行
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            ByteArrayOutputStream sb = new ByteArrayOutputStream();
            byte[] bt = new byte[1024];
            int length;
            while ((length = inputStream.read(bt)) != -1){
                sb.write(bt, 0, length);
            }
            System.out.println("[接受消息]"+sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
                /*socket.close();
                serverSocket.close();*/
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
