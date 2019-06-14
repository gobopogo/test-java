package com.misslyr.test.redis;

import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @Author missli
 * @Description
 * @Date 2019/6/14 17:00
 **/
public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",9999);
        jedis.set("name","小明");

        /*Socket socket = null;
        OutputStream outputStream = null;
        Writer writer = null;
        try {
            socket = new Socket("127.0.0.1", 9999);
            outputStream = socket.getOutputStream();
            if(writer == null){
                writer = new OutputStreamWriter(outputStream,"UTF-8");
            }
            writer.write("你好，我是小明！");
            System.out.println("消息发送成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
                outputStream.close();
                *//*socket.close();*//*
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }
}
