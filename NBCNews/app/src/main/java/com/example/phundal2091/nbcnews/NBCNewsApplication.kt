package com.example.phundal2091.nbcnews

import android.app.ActivityManager
import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.cache.DefaultBitmapMemoryCacheParamsSupplier
import com.facebook.imagepipeline.core.ImagePipelineConfig

/**
 * Created by phundal2091 on 5/10/18.
 */
class NBCNewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        // used for fresco, to control large image sizes
        val config = ImagePipelineConfig.newBuilder(this)
                .setBitmapMemoryCacheParamsSupplier(DefaultBitmapMemoryCacheParamsSupplier(activityManager))
                .setResizeAndRotateEnabledForNetwork(true)
                .setDownsampleEnabled(true)
                .build()
        Fresco.initialize(this, config)
    }
}