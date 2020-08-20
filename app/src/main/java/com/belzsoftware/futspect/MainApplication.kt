package com.belzsoftware.futspect

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import coil.util.CoilUtils
import com.belzsoftware.futspect.util.NIGHT_MODE_PREF
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
        setNightMode()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .crossfade(true)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(applicationContext))
                    .build()
            }
            .placeholder(R.drawable.ic_loop)
            .componentRegistry {
                add(SvgDecoder(applicationContext))
            }
            .build()
    }

    private fun setUpTimber() {
        if (!BuildConfig.DEBUG) {
            return
        }

        Timber.plant(Timber.DebugTree())
    }

    private fun setNightMode() {
        val pref = PreferenceManager
            .getDefaultSharedPreferences(applicationContext)
            .getInt(NIGHT_MODE_PREF, AppCompatDelegate.MODE_NIGHT_NO)

        AppCompatDelegate.setDefaultNightMode(pref)
    }
}