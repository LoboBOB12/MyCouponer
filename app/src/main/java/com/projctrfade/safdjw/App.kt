package com.projctrfade.safdjw

import android.app.Application
import com.onesignal.OneSignal

private var locator: StorageServiceLocator? = null
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        locator = StorageServiceLocator(this)
    }

    fun getServiceLocator(): StorageServiceLocator? {
        return locator
    }
}