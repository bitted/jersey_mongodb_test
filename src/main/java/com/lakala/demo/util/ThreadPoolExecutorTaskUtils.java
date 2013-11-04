package com.lakala.demo.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 类名称：ThreadPoolExecutorTaskUtils
 * 类描述：(线程池任务工具类)
 *		    (可处理多线程任务）
 * 创建人：Jack
 * 创建时间：2012-11-21 下午04:21:58
 * 修改人：
 * 修改时间：2012-11-21 下午04:21:58
 * 修改备注：
 * @version 1.0.0
 */

public class ThreadPoolExecutorTaskUtils {
/**
	corePoolSize： 线程池维护线程的最少数量

	maximumPoolSize：线程池维护线程的最大数量

	keepAliveTime： 线程池维护线程所允许的空闲时间

	unit： 线程池维护线程所允许的空闲时间的单位

	workQueue： 线程池所使用的缓冲队列

	handler： 线程池对拒绝任务的处理策略
**/
	
	private static ThreadPoolExecutor poolExecutor;
	
	private static ThreadPoolExecutorTaskUtils instance;
	
	private static int corePoolSize = 20;
	
	private static int maximumPoolSize = 50;
	
	private static long keepAliveTime = 1000;
	
	private static int queueSize = 100;
	
	public ThreadPoolExecutorTaskUtils(){
		if(poolExecutor == null){  
			poolExecutor = new ThreadPoolExecutor(
				corePoolSize, 
				maximumPoolSize,
				keepAliveTime, 
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(queueSize), new ThreadPoolExecutor.CallerRunsPolicy());
			}
	}
	/**
	 * 
	 * getInstance(获取线程池单例)
	 * @return
	 * 返回类型：ThreadPoolExecutorTaskUtils
	 * @exception
	 * @since  1.0.0
	 */
	public static synchronized ThreadPoolExecutorTaskUtils getInstance(){
		if(instance == null){
			instance = new ThreadPoolExecutorTaskUtils();
		}
		return instance;
	}
	
	/**
	 * 
	 * getThreadPoolExecutor(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @return
	 * 返回类型：ThreadPoolExecutor
	 * @exception
	 * @since  1.0.0
	 */
	public static synchronized ThreadPoolExecutor getThreadPoolExecutor(){
		if(poolExecutor == null){  
		poolExecutor = new ThreadPoolExecutor(
			corePoolSize, 
			maximumPoolSize,
			keepAliveTime, 
			TimeUnit.SECONDS, 
			new ArrayBlockingQueue<Runnable>(queueSize), new ThreadPoolExecutor.CallerRunsPolicy());
		}
		return poolExecutor;
		
	}
	
	public void executeTask(Runnable command){
		poolExecutor.execute(command);
	}
}
class MyHandler implements RejectedExecutionHandler{

	
	/* (non-Javadoc)
	 * @see java.util.concurrent.RejectedExecutionHandler#rejectedExecution(java.lang.Runnable, java.util.concurrent.ThreadPoolExecutor)
	 */
	private static int rejectedCount = 1;
	@Override
	public void rejectedExecution(Runnable arg0, ThreadPoolExecutor arg1) {
		System.out.println("拒绝的任务"+rejectedCount++);
		arg0.run();//运行完被抛弃的任务
		System.out.println("完成任务数："+arg1.getCompletedTaskCount());
		System.out.println("总任务数："+arg1.getTaskCount());
		
	}
	
}
