package com.astrapay

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun<T> LiveData<T>.getOrWaitValue(): T {
    var data: T? = null
    val latch = CountDownLatch(1)

    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            this@getOrWaitValue.removeObserver(this)
            latch.countDown() // stop waiting
        }
    }

    this.observeForever(observer)

    try {
        if (!latch.await(2, TimeUnit.SECONDS)) {
            throw TimeoutException("Live data is timeout.")
        }
    } finally {
        this.removeObserver(observer)
    }

    return data!! as T
}