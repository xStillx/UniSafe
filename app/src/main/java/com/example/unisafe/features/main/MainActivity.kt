package com.example.unisafe.features.main

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.example.unisafe.R
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.common.ApiException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getToken()
    }

    private fun getToken() {
        // Create a thread.
        object : Thread() {
            override fun run() {
                try {
                    // Obtain the app ID from the agconnect-services.json file.  5765880207853176293
                    val appId = "5765880207853176293"

                    // Set tokenScope to HCM.
                    val tokenScope = "HCM"
                    val token = HmsInstanceId.getInstance(this@MainActivity).getToken(appId, tokenScope)
                    Log.i(TAG, "get token:$token")

                    // Check whether the token is null.
                    if (!TextUtils.isEmpty(token)) {
                        sendRegTokenToServer(token)
                    }
                } catch (e: ApiException) {
                    Log.e(TAG, "get token failed, $e")
                }
            }
        }.start()
    }
    private fun sendRegTokenToServer(token: String) {
        Log.i(TAG, "sending token to server. token:$token")
    }
}