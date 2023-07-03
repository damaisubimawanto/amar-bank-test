package com.damai.base.extension

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import com.damai.base.util.Event
import com.damai.base.util.EventObserver

/**
 * Created by damai007 on 04/July/2023
 */

fun <T> FragmentActivity.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this) { it?.let { t -> action(t) } }
}

@JvmName("observeEvent")
fun <T> FragmentActivity.observe(liveData: LiveData<Event<T>>, observer: EventObserver<T>) {
    liveData.observe(this, observer)
}