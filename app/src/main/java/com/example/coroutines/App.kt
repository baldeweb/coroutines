package com.example.coroutines

import android.app.Application
import com.example.coroutines.data.repositoryModules
import com.example.coroutines.data.serviceModules
import com.example.coroutines.data.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                    listOf(
                            viewModelModules,
                            repositoryModules,
                            serviceModules
                    )
            )
        }
    }
}