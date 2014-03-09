package com.novoda.sexp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;


public abstract class SEXPBaseService extends Service {

    public static boolean DEBUG = true;
    private static final String TAG = SEXPBaseService.class.getSimpleName();
    public static final String EXTRA_ID_API = "com.novoda.sexp.service.EXTRA_ID_API";
    public static final String EXTRA_CANCEL_TASK_TYPE_ID = "com.novoda.sexp.service.EXTRA_CANCEL_TASK_ID";

    protected SEXPQueue mQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        mQueue = SEXPQueue.getInstance();
        if(DEBUG){
        	Log.d(TAG, "OnCreate");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	if(DEBUG){
        	Log.d(TAG, "OnStartCommand");
        }
    	
        if (intent == null) {
            if (DEBUG) {
                Log.w(TAG, "onStartCommand() - The intent received is null...");
            }
            return START_NOT_STICKY;
        }

        final Bundle params = intent.getExtras();
        if (params == null) {
            if (DEBUG) {
                Log.w(TAG, "onStartCommand() - The bundle from the received intent is null...");
            }
            return START_NOT_STICKY;
        }

        if (DEBUG) {
            Log.d(TAG, "onStartCommand() - Bundle=" + params);
        }

        final int idApi = params.getInt(EXTRA_ID_API, -1);
        final int candelIdApi = params.getInt(EXTRA_CANCEL_TASK_TYPE_ID, -1);

        if (idApi == -1 && candelIdApi == -1) {
            if (DEBUG) {
                Log.w(TAG, "onStartCommand() - The idApi is missing from the received intent...");
            }
            return START_NOT_STICKY;
        }

        if (!checkParams(idApi, params)) {
            if (DEBUG) {
                Log.w(TAG, "onStartCommand() - The service call is missing parameters from the received intent...");
            }
            return START_NOT_STICKY;
        }

        if( candelIdApi != -1){
        	manageApiCancel(params, candelIdApi);
        }
        
        manageApiCall(params, idApi);
        

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        
    	if(DEBUG){
    		Log.d(TAG, "Destroying service");
    	}

        mQueue.cancelAll();
    }

    protected abstract void manageApiCall(Bundle params, int idApi);
    protected abstract void manageApiCancel(Bundle params, int idApi);
    protected abstract boolean checkParams(int idApi, Bundle params);
}
