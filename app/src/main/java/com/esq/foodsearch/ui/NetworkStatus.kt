package com.esq.foodsearch.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.biodun.networkMonitorManager.NetworkMonitorManager

 class NetworkStatus(context: Context) : LifecycleObserver {
    val TAG = this::class.simpleName

    init {
        (context as AppCompatActivity).lifecycle.addObserver(this)
    }

    val networkMonitorManager: NetworkMonitorManager by lazy {
        NetworkMonitorManager.getInstance(context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.d(TAG, "OnStart")
        networkMonitorManager.registerCallback()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.d(TAG, "OnStop")
        networkMonitorManager.unRegisterCallback()
    }

 }
