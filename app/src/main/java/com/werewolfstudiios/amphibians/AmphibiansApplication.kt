package com.werewolfstudiios.amphibians

import android.app.Application
import com.werewolfstudiios.amphibians.data.AppContainer
import com.werewolfstudiios.amphibians.data.DefaultAppContainer

class AmphibiansApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
