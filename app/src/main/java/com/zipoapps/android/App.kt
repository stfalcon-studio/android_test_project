package com.zipoapps.android

import android.app.Application
import com.github.ajalt.timberkt.Timber
import com.zipoapps.android.common.di.appModule
import com.zipoapps.data.di.dataModule
import com.zipoapps.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initDI()
        initTimber()
    }

    private fun initDI() {
        GlobalContext.startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(
                *appModule,
                *dataModule,
                *domainModule,
            )
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}