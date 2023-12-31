package com.taweewong.coverqr

import android.app.Application
import com.taweewong.coverqr.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }
}