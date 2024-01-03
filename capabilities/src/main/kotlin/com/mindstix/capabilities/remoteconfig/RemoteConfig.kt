package com.mindstix.capabilities.remoteconfig

import android.annotation.SuppressLint
import android.app.Activity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.mindstix.capabilities.R
import com.mindstix.core.logger.Logger

object RemoteConfig {
    private val mTAG = javaClass.simpleName

    const val KEY_1 = "rc_key1"
    const val KEY_2 = "rc_key2"

    private const val MINIMUM_FETCH_INTERVAL: Long = 3600  //In seconds


    @SuppressLint("StaticFieldLeak")
    private lateinit var remoteConfig: FirebaseRemoteConfig

    fun initialise() {
        remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = MINIMUM_FETCH_INTERVAL
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }

    fun getInstance() = remoteConfig

    fun fetchAndActivate(activity: Activity) {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Logger.d(mTAG, "Config params updated: $updated")
                    val value1 = remoteConfig.getString(KEY_1)
                    val value2 = remoteConfig.getString(KEY_2)

                    Logger.d(mTAG, "Remote Config value1: $value1 --- value2: $value2")
                } else {
                    Logger.d(mTAG, "Fetch failed")
                }
            }
    }
}