package com.mlefrapper.androidstarterkit

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import com.mlefrapper.core.util.ReleaseTimberTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AndroidStarterKitApplication : Application() {

    lateinit var networkFlipperPlugin: NetworkFlipperPlugin

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        setupFlipper()
    }

    private fun setupTimber() {
        Timber.plant(
            if (BuildConfig.DEBUG) {
                Timber.DebugTree()
            } else {
                ReleaseTimberTree()
            },
        )
    }

    private fun setupFlipper() {
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            SoLoader.init(this, false)
            val flipperClient = AndroidFlipperClient.getInstance(this)
            networkFlipperPlugin = NetworkFlipperPlugin()
            flipperClient.addPlugin(
                InspectorFlipperPlugin(
                    this@AndroidStarterKitApplication,
                    DescriptorMapping.withDefaults(),
                ),
            )
            flipperClient.addPlugin(DatabasesFlipperPlugin(this@AndroidStarterKitApplication))
            flipperClient.addPlugin(networkFlipperPlugin)
            flipperClient.start()
        }
    }
}
