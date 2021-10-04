package com.example.mylivedata

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {
    companion object {
        private const val ONE_SECOND = 1000
    }
    private val mInitialTime = SystemClock.elapsedRealtime()
    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }
}