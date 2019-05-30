package view;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest01 implements Runnable {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(1);

		executorService.submit(new ThreadPoolTest01());

		//executorService.execute(new ThreadPoolTest01());
		//以上两种方式最终在底层都是调用了java.util.concurrent.ThreadPoolExecutor.execute()方法

		executorService.shutdown();

		//等价于
		//ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
		//															   1,
		//															   0L,
		//															   TimeUnit.MILLISECONDS,
		//															   new LinkedBlockingQueue<Runnable>());
		//threadPoolExecutor.submit(new ThreadPoolTest01());
		//threadPoolExecutor.execute(new ThreadPoolTest01());

		//threadPoolExecutor.shutdown();

	}

	@Override
	public void run() {
		System.out.println("学习使我快乐");
	}
}
