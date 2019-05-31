package com.misslyr.test.juc;

/**
 * @Author missli
 * @Description volatile: 当多个线程进行操作内存中的共享变量是，volatile可以保证内存中数据可见，
 * 使用volatile修饰的变量从线程内 方法栈中同步到 堆（对象） 中，所以效率不是很高，但比 synchronized 效率要高，更轻量级
 * 注意：
 * volatile 不具有互斥性，多个线程可以同时在主存中访问这个变量
 * volatile 不能保证变量的原子性（不可分割）
 * @Date 2019/5/14 17:12
 **/
public class TestVolatile {

    public static void main(String[] args) {
        //多线程实现1 实现Runable接口
        ThreadDemo thread1 = new ThreadDemo();
        new Thread(thread1).start();

        while (true){
            if(thread1.isFlag()){
                System.out.println("------=Runable=========-");
                break;
            }
        }

        //多线程实现2 继承Thread
        ThreadDemo1 th2 = new ThreadDemo1();
        th2.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            if(th2.isFlag()){
                System.out.println("------==Thread========-");
                break;
            }
        }
    }

}

class ThreadDemo implements Runnable{

    //去掉volatile 试试
    private volatile boolean flag = false;

    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;

        System.out.println("flag的值："+flag);

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class ThreadDemo1 extends Thread{
    private boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}