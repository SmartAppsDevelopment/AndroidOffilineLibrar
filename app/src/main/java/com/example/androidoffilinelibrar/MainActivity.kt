package com.example.androidoffilinelibrar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitoffline.OfflineCacheRetrofit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //OfflineCacheRetrofit.getInstance(this,"",String::class.java).writeToFile()
    }
}