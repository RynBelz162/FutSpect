package com.belzsoftware.futspect

import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.util.CoilUtils
import com.belzsoftware.futspect.di.DaggerAppComponent
import com.belzsoftware.futspect.util.NIGHT_MODE_PREF
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import okhttp3.OkHttpClient

class MainApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        setNightMode()
        setUpCoil()
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

    private fun setUpCoil() {
        Coil.setDefaultImageLoader {
            ImageLoader(applicationContext) {
                crossfade(true)
                okHttpClient {
                    OkHttpClient.Builder()
                        .cache(CoilUtils.createDefaultCache(applicationContext))
                        .build()
                }
                componentRegistry {
                    add(SvgDecoder(applicationContext))
                }
                placeholder(R.drawable.ic_loop)
            }
        }
    }
}