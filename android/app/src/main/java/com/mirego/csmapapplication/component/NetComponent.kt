package com.mirego.csmapapplication.component

import com.mirego.csmapapplication.activity.MainActivity
import com.mirego.csmapapplication.module.AppModule
import com.mirego.csmapapplication.module.NetModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface NetComponent {
    fun inject(activity: MainActivity)
}