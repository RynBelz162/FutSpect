package com.belzsoftware.futspect

import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.belzsoftware.futspect.di.DaggerAppComponent
import com.belzsoftware.futspect.util.NIGHT_MODE_PREF
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        setNightMode()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    private fun setNightMode() {
        val pref = PreferenceManager
            .getDefaultSharedPreferences(applicationContext)
            .getInt(NIGHT_MODE_PREF, AppCompatDelegate.MODE_NIGHT_NO)

        AppCompatDelegate.setDefaultNightMode(pref)
    }
}