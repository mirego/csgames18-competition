package com.mirego.csmapapplication

import android.app.Application
import com.mirego.csmapapplication.component.DaggerNetComponent
import com.mirego.csmapapplication.component.NetComponent
import com.mirego.csmapapplication.module.AppModule
import com.mirego.csmapapplication.module.NetModule

class MapPingApplication : Application() {
    lateinit var netComponent: NetComponent

    override fun onCreate() {
        super.onCreate()

        netComponent = DaggerNetComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule(DATA_SOURCE_URL))
            .build()
    }

    companion object {
        private const val DATA_SOURCE_URL = "https://api.github.com"
    }
}