package com.damai.base.extension

import android.view.View

/**
 * Created by damai007 on 03/July/2023
 */

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}