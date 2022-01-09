package com.astrapay.jason_ajaib_test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.astrapay.jason_ajaib_test.helper.data.EventData
import com.astrapay.jason_ajaib_test.helper.exception.UnAuthorizedException
import kotlinx.coroutines.CoroutineExceptionHandler

open class MainViewModel : ViewModel() {

    val liveError: MutableLiveData<EventData<String>> by lazy { MutableLiveData<EventData<String>>() }
    val liveInvalidToken: MutableLiveData<EventData<String>> by lazy { MutableLiveData<EventData<String>>() }

    protected val exceptionHandler = CoroutineExceptionHandler { _, ex ->
        if ( ex is UnAuthorizedException) {
            liveInvalidToken.value = EventData()
        }
        else if ( ex.message?.isNotEmpty() == true ) {
            liveError.value = EventData(
                message = ex.message!!
            )
        }
    }
}