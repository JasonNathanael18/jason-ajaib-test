package com.astrapay.jason_ajaib_test.helper.data

open class EventData<out T>(val message: String = "OK",
                            val content: T? = null) {

    private var hasBeenHandled = false
    fun getContentIfNotHandled(): Boolean {
        return if (hasBeenHandled) {
            false
        } else {
            hasBeenHandled = true
            true
        }
    }
}