package view;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExample implements Runnable {

	AtomicInteger atomicInteger = new AtomicInteger();

	@Override
	public void run() {
		int task = atomicInteger.incrementAndGet();
		System.out.println("task：" + task);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//如果corePoolSizex 小于for循环的最大次数，则会被拒绝
		ThreadPoolExecutor threadPoolExecutor =
				new ThreadPoolExecutor(1,
									   1,
									   0L,
									   TimeUnit.MILLISECONDS,
									   new ArrayBlockingQueue<Runnable>(1),  //队列默认只存一个线程
									   //new LinkedBlockingDeque<>(),
									   new RejectedExecutionHandler() {
										   @Override
										   public void rejectedExecution(Runnable r,
																		 ThreadPoolExecutor executor) {
											   System.out.println("当前被拒绝任务："+r.toString());
										   }
									   }

				){
					@Override
					protected void beforeExecute(Thread t, Runnable r) {
						//super.beforeExecute(t, r);

						System.out.println(r.getClass().getName()+ "执行线程之前");
					}

					@Override
					protected void afterExecute(Runnable r, Throwable t) {
						//super.afterExecute(r, t);
						System.out.println("执行线程之后");
					}
				};

		//也可以直接设置 setRejectedExecutionHandler()， 重写线程被拒接处理函数
		/*threadPoolExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r,
										  ThreadPoolExecutor executor) {
				System.out.println(r.toString()+"我被拒绝了");
			}
		});*/

		ThreadPoolExample threadPoolExample = new ThreadPoolExample();
		for (int i=0; i<20; i++){
			threadPoolExecutor.execute(threadPoolExample);  //输出 view.ThreadPoolExample执行线程之前
			//threadPoolExecutor.submit(threadPoolExample);  // 输出 java.util.concurrent.FutureTask执行线程之前

			// 产生这种情况是因为什么？
			// submit()方法中 RunnableFuture<Void> ftask = newTaskFor(task, null); new了一个java的任务
		}

		threadPoolExecutor.shutdown();

	}
}
