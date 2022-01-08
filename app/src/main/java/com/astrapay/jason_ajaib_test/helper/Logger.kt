package com.astrapay.jason_ajaib_test.helper

import android.util.Log
import java.lang.Exception

object Logger {
    fun i(msg: String) {
        Log.i(DefaultConstants.logTag, msg)
    }

    fun d(msg: String) {
        Log.d(DefaultConstants.logTag, msg)
    }

    fun e(msg: String) {
        Log.e(DefaultConstants.logTag, msg)
    }

    fun e(msg: String, e: Exception) {
        Log.e(DefaultConstants.logTag, msg, e)
    }
}