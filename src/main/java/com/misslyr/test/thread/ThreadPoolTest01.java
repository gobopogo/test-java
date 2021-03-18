package com.misslyr.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest01 implements Runnable {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		//executorService.submit(new ThreadPoolTest01());

		//executorService.execute(new ThreadPoolTest01());
		//以上两种方式最终在底层都是调用了java.util.concurrent.ThreadPoolExecutor.execute()方法

		//executorService.shutdown();

		//等价于
		//ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
		//															   1,
		//															   0L,
		//															   TimeUnit.MILLISECONDS,
		//															   new LinkedBlockingQueue<Runnable>());
		//threadPoolExecutor.submit(new ThreadPoolTest01());
		//threadPoolExecutor.execute(new ThreadPoolTest01());

		//threadPoolExecutor.shutdown();

		for(int i=1; i<10000;i++){
			executorService.execute(new ThreadPoolTest01());
		}
		for(int i=1; i<10000;i++){
			executorService.execute(new ThreadPoolTest01());
		}
	}

	@Override
	public void run() {
		System.out.println("concurrentThread:{"+Thread.currentThread().getName());
	}
}
