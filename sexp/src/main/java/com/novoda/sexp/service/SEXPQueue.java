package com.novoda.sexp.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.util.Log;

/**
 * Created by Ianic on 3/3/14.
 */
public class SEXPQueue {
	
    private static final String TAG = SEXPQueue.class.getSimpleName();
    private static final boolean DEBUG = SEXPBaseService.DEBUG;
	
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT;

    private final BlockingQueue<Runnable> mWorkQueue;
    private final ThreadPoolExecutor mThreadPool;

    // A single instance of SEXPQueue, used to implement the singleton pattern
    private static SEXPQueue sInstance = null;
    static {
        // The time unit for "keep alive" is in seconds
        KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        // Creates a single static instance of PhotoManager
        sInstance = new SEXPQueue();
    }

    private SEXPQueue (){
    	if(DEBUG){
    		Log.d(TAG, "Creating Queue");
    	}
        mWorkQueue = new LinkedBlockingQueue<Runnable>();
        mThreadPool = new ThreadPoolExecutor(
                NUMBER_OF_CORES,       // Initial pool size
                NUMBER_OF_CORES,       // Max pool size
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                mWorkQueue);
    }

    public static SEXPQueue getInstance(){
        return sInstance;
    }

    public void addToQueue(SEXPTask task){
    	if(DEBUG){
    		Log.d(TAG, "Adding task to queue");
    	}
        mThreadPool.execute(task);
    }

    public void cancelAll() {
        /*
         * Creates an array of Runnables that's the same size as the
         * thread pool work queue
         */
        SEXPTask[] runnableArray = new SEXPTask[mWorkQueue.size()];
        // Populates the array with the Runnables in the queue
        mWorkQueue.toArray(runnableArray);
        // Stores the array length in order to iterate over the array
        int len = runnableArray.length;
        /*
         * Iterates over the array of Runnables and interrupts each one's Thread.
         */
        synchronized (this) {
            // Iterates over the array of tasks
            for (int runnableIndex = 0; runnableIndex < len; runnableIndex++) {
                // Gets the current thread
                Thread thread = runnableArray[runnableIndex].getCurrentThread();
                // if the Thread exists, post an interrupt to it
                if (null != thread) {
                    thread.interrupt();
                }
            }
        }
    }

    public void cancelTaskByType(int id){
        int type = SEXPTask.getType(id);

        SEXPTask[] runnableArray = new SEXPTask[mWorkQueue.size()];
        mWorkQueue.toArray(runnableArray);
        if (DEBUG){
            Log.d(TAG, "Cancelling taks by type: " + type + ". Queue size: " + mWorkQueue.size());
        }
        synchronized (this) {

            for (SEXPTask task : runnableArray) {
                if ( SEXPTask.getType(task.getId()) == type ){
                    Thread thread = task.getCurrentThread();
                    // if the Thread exists, post an interrupt to it
                    if (null != thread) {
                        thread.interrupt();
                    }
                }

            }
        }
    }

    public void removeTask(SEXPTask sexpTask, int id) {

        if (sexpTask != null && sexpTask.getId() == id) {

            /*
             * Locks on this class to ensure that other processes aren't mutating Threads.
             */
            synchronized (this) {

                // Gets the Thread that the task is running on
                Thread thread = sexpTask.getCurrentThread();

                // If the Thread exists, posts an interrupt to it
                if (null != thread)
                    thread.interrupt();
            }
            /*
             * Removes the parsing Runnable from the ThreadPool. This opens a Thread in the
             * ThreadPool's work queue, allowing a task in the queue to start.
             */
            mThreadPool.remove(sexpTask);
        }
    }

}
