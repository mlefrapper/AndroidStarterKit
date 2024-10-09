package com.mlefrapper.androidstarterkit

import android.app.Application
import com.mlefrapper.core.util.ReleaseTimberTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AndroidStarterKitApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        Timber.plant(
            if (BuildConfig.DEBUG) Timber.DebugTree() else ReleaseTimberTree(),
        )
    }
}
