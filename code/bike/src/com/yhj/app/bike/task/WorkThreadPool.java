package com.yhj.app.bike.task;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WorkThreadPool {
	int coreSize;
	int maxPoolSize;
	ThreadPoolExecutor threadPoolExecutor = null;
	ArrayBlockingQueue<Runnable> workQueue;
	private static WorkThreadPool threadPool;
	private WorkThreadPool() {
		Runtime runtime = Runtime.getRuntime();
		coreSize = runtime.availableProcessors();
		maxPoolSize = coreSize * 2;
		workQueue = new ArrayBlockingQueue<Runnable>(maxPoolSize * 5);
		threadPoolExecutor = new ThreadPoolExecutor(coreSize, maxPoolSize, 2*1000, TimeUnit.SECONDS, workQueue);
	}

	public synchronized static WorkThreadPool getThreadPool() {
		if (threadPool == null) {
			threadPool = new WorkThreadPool();
		}
		return threadPool;
	}

	public  void runTask(Runnable runnable){
		threadPoolExecutor.execute(runnable);
	}

	public  void runTasks(ArrayList<Runnable> runnables) {
		for (Runnable runnable : runnables) {
			threadPoolExecutor.execute(runnable);
		}
	}
	public void shutdown(){
		threadPoolExecutor.shutdown();
	}
}
