package com.misslyr.test.thread;

/**
 * 线程实现2 Runnable 接口
 */
public class Runnable01 implements Runnable {

	@Override
	public void run() {
		System.out.println("学习使我快乐！");
	}

	public static void main(String[] args) {
		new Runnable01().run();  //方法调用

		new Thread(new Runnable01()).start();  //线程启动
	}
}
