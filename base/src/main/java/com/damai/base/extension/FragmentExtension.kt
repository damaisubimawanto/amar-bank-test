package com.damai.base.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import com.damai.base.R
import com.damai.base.util.Event
import com.damai.base.util.EventObserver

/**
 * Created by damai007 on 03/July/2023
 */

fun <T> Fragment.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    with(viewLifecycleOwner) {
        liveData.observe(this) { it?.let { t -> action(t) } }
    }
}

@JvmName("observeEvent")
fun <T> Fragment.observe(liveData: LiveData<Event<T>>,
                         observer: EventObserver<T>
) {
    with(viewLifecycleOwner) { liveData.observe(this, observer) }
}

fun FragmentTransaction.slideTransition() {
    setCustomAnimations(
        R.anim.enter_from_right,
        R.anim.exit_to_left,
        R.anim.enter_from_left,
        R.anim.exit_to_right
    )
}