package com.astrapay.jason_ajaib_test.helper.data

import androidx.lifecycle.Observer

class EventObserver<T>(private val onEventUnhandledContent: (EventData<T>) -> Unit) :
    Observer<EventData<T>> {

    override fun onChanged(event: EventData<T>?) {
        event?.getContentIfNotHandled()?.let {
            if (it) {
                onEventUnhandledContent(event)
            }
        }
    }
}