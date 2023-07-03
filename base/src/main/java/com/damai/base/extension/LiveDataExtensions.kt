package com.damai.base.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by damai007 on 03/July/2023
 */

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>