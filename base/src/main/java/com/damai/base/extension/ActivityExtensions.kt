package com.damai.base.extension

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import com.damai.base.util.Event
import com.damai.base.util.EventObserver

/**
 * Created by damai007 on 04/July/2023
 */

fun <T> FragmentActivity.observe(
    liveData: LiveData<T>,
    action: (t: T) -> Unit
) {
    liveData.observe(this) { it?.let { t -> action(t) } }
}

@JvmName("observeEvent")
fun <T> FragmentActivity.observe(
    liveData: LiveData<Event<T>>,
    observer: EventObserver<T>
) {
    liveData.observe(this, observer)
}

fun FragmentActivity?.hideKeyboard() {
    this?.currentFocus?.let { view ->
        val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Context.showToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}