package com.example.part_instegram

import android.app.Application
import android.util.Log
import com.parse.Parse
import com.parse.ParseObject


class ParseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ParseObject.registerSubclass(Post::class.java)
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        val firstObject = ParseObject("FirstClass")
        firstObject.put("message", "Hey ! First message from android. Parse is now connected")
        firstObject.saveInBackground {
            if (it != null) {
                Log.e("MainActivity", "Error message Not saved")
            } else {
                Log.e("MainActivity", "Object saved.")
            }
        }
    }

}