package view;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Callable01 implements Callable{

	@Override
	public String call() throws Exception {
		return "学习使我快乐！";
	}

	public static void main(String[] args) throws Exception {
		new Callable01().call();  //方法调用

		//线程调用
		FutureTask task = new FutureTask<>(new Callable01());  //异步，也实现了Runable接口
		new Thread(task).start();

		System.out.println(task.get()); //get到返回值

	}
}
