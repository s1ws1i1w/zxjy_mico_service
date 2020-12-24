package com.hdyl.schedule.xxljob.threadpool;

import com.hdyl.merchant.union.common.utils.date.DateUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleTransferOrderThreadPoolManager {

	private static ScheduleTransferOrderThreadPoolManager instance;

	//创建等待队列
	private BlockingQueue<Runnable> queue = null;
    //创建线程池，池中保存的线程数为3，允许的最大线程数为5
	private ThreadPoolExecutor pool = null;

	public static ScheduleTransferOrderThreadPoolManager getExecutorManager(){
		if(instance == null){
			 synchronized (ScheduleTransferOrderThreadPoolManager.class) {
				 if(instance == null){
					 instance = new ScheduleTransferOrderThreadPoolManager();
	             }
	         }
		}
		return instance;
	}

	public ScheduleTransferOrderThreadPoolManager(){
		queue = new ArrayBlockingQueue<Runnable>(2000);
		/**
		 * corePoolSize：线程池中所保存的核心线程数，包括空闲线程。
		 * maximumPoolSize：池中允许的最大线程数。
		 * keepAliveTime：线程池中的空闲线程所能持续的最长时间。
		 * unit：持续时间的单位。
		 * workQueue：任务执行前保存任务的队列，仅保存由 execute 方法提交的 Runnable 任务。
		 */
		pool = new ThreadPoolExecutor(50,150,30,TimeUnit.SECONDS,queue, (r) -> {
			Thread thread = new Thread(r);
			thread.setName("ScheduleTransferThreadPool");
			return thread;
		}, new ThreadPoolExecutor.AbortPolicy());
	}
	
	
	public void execute(Runnable runnable){
		if(pool != null){
			pool.execute(runnable);
		}
	}

	public void submit(Runnable runnable){
		if(pool != null){
			pool.submit(runnable);
		}
	}
	
	public void remove(Runnable runnable){
		if(pool != null){
			pool.remove(runnable);
		}
	}
	
	public void destroyed(){
		if(pool != null){
			pool.shutdown();
			queue.clear();
			instance = null;
		}
	}
	
	public String printlnPool(){
		if(pool == null){
			return null;
		}
		StringBuilder sb = new StringBuilder("==========ScheduleTransferorderThreadPoolManager迁移支付订单线程开始-"+ DateUtil.getDateTimeStr() +"==========");
		sb.append("\npool: ActiveCount->").append(pool.getActiveCount());
		sb.append("\npool: CorePoolSize->").append(pool.getCorePoolSize());
		sb.append("\npool: MaximumPoolSize->").append(pool.getMaximumPoolSize());
		sb.append("\npool: TaskCount->").append(pool.getTaskCount());
		sb.append("\npool: KeepAliveTime->").append(pool.getKeepAliveTime(TimeUnit.SECONDS)).append(" s");
		sb.append("\npool: LargestPoolSize->").append(pool.getLargestPoolSize());
		sb.append("\npool: PoolSize->").append(pool.getPoolSize());
		sb.append("\n==========ScheduleTransferThreadPool 汇总线程结束-").append(DateUtil.getDateTimeStr()).append("==========");
		System.out.println(sb.toString());
		return sb.toString();
	}

}
