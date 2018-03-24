package com.mirego.csmapapplication

import android.app.Application
import android.util.Log
import com.mirego.csmapapplication.component.DaggerNetComponent
import com.mirego.csmapapplication.component.NetComponent
import com.mirego.csmapapplication.model.Piece
import com.mirego.csmapapplication.module.AppModule
import com.mirego.csmapapplication.module.NetModule
import retrofit2.Retrofit
import javax.inject.Inject

class MapPingApplication : Application() {
    lateinit var netComponent: NetComponent

    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()

        netComponent = DaggerNetComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule(DATA_SOURCE_URL))
            .build()
        netComponent.inject(this)
    }

    companion object {
        private const val DATA_SOURCE_URL = "http://s3.amazonaws.com/"
        lateinit var pieces: List<Piece>
    }
}