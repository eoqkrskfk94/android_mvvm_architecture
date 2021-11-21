package com.mj.mvvmpatternframe

import android.app.Application
import com.mj.mvvmpatternframe.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        //Koin setup
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApp)
            modules(appModule)
        }
    }

}