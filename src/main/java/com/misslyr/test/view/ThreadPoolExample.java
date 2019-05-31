package com.misslyr.test.view;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExample implements Runnable {

	AtomicInteger atomicInteger = new AtomicInteger();

	// 启动20个线程测试一下
	Map conMap = new ConcurrentHashMap();

	@Override
	public void run() {
		int task = atomicInteger.incrementAndGet();
		System.out.println("task：" + task);


		conMap.put(task,"哈哈哈");
		System.out.println(conMap);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//如果corePoolSizex 小于for循环的最大次数，则会被拒绝
		ThreadPoolExecutor threadPoolExecutor =
				new ThreadPoolExecutor(10,
									   10,
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
		for (int i=0; i<10; i++){
			threadPoolExecutor.execute(threadPoolExample);  //输出 view.ThreadPoolExample执行线程之前
			//threadPoolExecutor.submit(threadPoolExample);  // 输出 java.util.concurrent.FutureTask执行线程之前

			// 产生这种情况是因为什么？
			// submit()方法中 RunnableFuture<Void> ftask = newTaskFor(task, null); new了一个java的任务
		}

		threadPoolExecutor.shutdown();

		// 如果任务数大于线程池最大核心数，任务就会被加入队列 workQueue.offer(command)，在队列中使用了对当前线程使用重入锁 ReentrantLock，保证入队列的原子操作
		/**
		 * public void execute(Runnable command) {
		 * 		int c = ctl.get();
		 * 		if (workerCountOf(c) < corePoolSize) {
		 * 		if (addWorker(command, true))
		 * 		return;
		 * 		c = ctl.get();
		 * 		}
		 * 		if (isRunning(c) && workQueue.offer(command)) {
		 * 		int recheck = ctl.get();
		 * 		if (!isRunning(recheck) && remove(command))
		 * 		reject(command);
		 * 		             else if (workerCountOf(recheck) == 0)
		 * 		addWorker(null, false);
		 * 		}
		 * 		         else if (!addWorker(command, false))
		 * 		reject(command);
		 * 		}
		 */
		//ReentrantLock lock = new ReentrantLock();
		//lock.tryLock();
		/**
		 * 跟进源码看，这个工作队列使用的数据结构是双向链表，Node节点属性 prev,next,data
		 *
		 * private boolean linkLast(Node<E> node) {
		 *         // assert lock.isHeldByCurrentThread();
		 *         if (count >= capacity)
		 *             return false;
		 *         Node<E> l = last;
		 *         node.prev = l;
		 *         last = node;
		 *         if (first == null)
		 *             first = node;
		 *         else
		 *             l.next = node;
		 *         ++count;
		 *         notEmpty.signal();
		 *         return true;
		 *     }
		 *
		 */


	}
}
