package com.shapeide.udarakita

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class UdaraApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(object: Timber.DebugTree(){
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, "ASD", message, t)
            }
        })
    }
}