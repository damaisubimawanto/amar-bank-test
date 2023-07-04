package com.damai.base.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Created by damai007 on 03/July/2023
 */

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.setCustomOnClickListener(listener: View.OnClickListener) {
    setOnClickListener(object : View.OnClickListener {
        var lastTimeClicked = 0L

        override fun onClick(p0: View?) {
            if (System.currentTimeMillis() - lastTimeClicked > 1_500L) {
                lastTimeClicked = System.currentTimeMillis()
                listener.onClick(p0)
            }
        }
    })
}

fun View.showDefaultSnackBar(message: String) {
    Snackbar.make(
        this,
        message,
        Snackbar.LENGTH_SHORT
    ).show()
}