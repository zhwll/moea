package com.xfactor.moea.base;

import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicUtils {
	
	public static Random random = new Random();

	private static ExecutorService defaultThreadPool = Executors.newCachedThreadPool();
	private static ScheduledExecutorService scheduledThreadPool;
	public static ConcurrentHashMap<String, Object> gloabMemData = new ConcurrentHashMap<String, Object>();

	
   // private static ResourceBundle resourceBundle;
	
	public static ScheduledExecutorService getScheduledThreadPool() {
		if (scheduledThreadPool == null) {
			int scct = Runtime.getRuntime().availableProcessors() * 2;
			scct = scct <= 4 ? 4 : scct;
			scheduledThreadPool = Executors.newScheduledThreadPool(scct);

		}
		return scheduledThreadPool;
	}

	public static void startNewThread(Runnable th) {
		if (th == null) {
			return;
		}
		if (defaultThreadPool == null) {
			defaultThreadPool = Executors.newCachedThreadPool();
		}
		defaultThreadPool.execute(th);
	}

	public static void stopAllThread(int sec) {

		try {
			defaultThreadPool.shutdown();
			if (!defaultThreadPool.awaitTermination(sec, TimeUnit.SECONDS)) {
				defaultThreadPool.shutdownNow();
			}
		} catch (InterruptedException e) {
			defaultThreadPool.shutdownNow();
		}

	}

	public static Logger getLogger() {
		return getLogger("DEFAULT");
	}

	public static Logger getLogger(String name) {
		
		return LoggerFactory.getLogger(name==null?"DEFAULT":name);
	}
	public static Logger getLogger(Class<?> cls) {
		
		return LoggerFactory.getLogger(cls==null?"DEFAULT":cls.getName());
	}
	
//	public static ResourceBundle getResourceBundle() {
//		return BasicUtils.resourceBundle;
//	}
//	public static void setResourceBundle(ResourceBundle resourceBundle) {
//		BasicUtils.resourceBundle = resourceBundle;
//	}
//    public static String getMessage(String s){
//        return BasicUtils.resourceBundle.getString(s);
//    }
//    public static String getMessage(String s,String defaultdata){
//        String data =  BasicUtils.resourceBundle.getString(s);
//        return ("".equals(data)||data==null)?defaultdata:data;
//    }
    public static String getRootPath() {
    	return System.getProperty("user.dir");
    }
}
