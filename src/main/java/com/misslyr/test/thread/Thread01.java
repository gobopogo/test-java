package com.misslyr.test.thread;

/**
 * 线程实现1 集成Thread 类
 */
public class Thread01 extends Thread {

	@Override
	public void run() {
		System.out.println("学习使我快乐");
	}

	public static void main(String[] args) {
		//new Thread01().run();  //方法调用，实际只有一个main线程,可通过idea调试窗口的快照看到线程

		new Thread01().start();  //线程启动， 在main线程中启动了一个thread-0线程


	}

}
