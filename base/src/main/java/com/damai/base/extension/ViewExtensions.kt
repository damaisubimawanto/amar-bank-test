package com.damai.base.extension

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupWindow
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

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

fun AppCompatEditText.addOnTextChanged(callback: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            callback.invoke(p0?.toString().orEmpty())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {}
    })
}

fun TextInputEditText.setupDropDownAdapter(
    activity: FragmentActivity?,
    list: List<String>,
    callback: (String) -> Unit
) {
    fun showDropDown(): PopupWindow {
        val popupWindow = PopupWindow(context)
        val arrayAdapter = ArrayAdapter(
            context,
            android.R.layout.select_dialog_item,
            list
        )
        val listView = ListView(context)
        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { _, _, position, _ ->
            list[position].let { selectedText ->
                setText(selectedText)
                callback.invoke(selectedText)
            }
            popupWindow.dismiss()
        }
        listView.isFocusable = true

        popupWindow.width = this.width
        popupWindow.height = WindowManager.LayoutParams.WRAP_CONTENT
        popupWindow.contentView = listView
        popupWindow.isOutsideTouchable = true
        popupWindow.showAsDropDown(this, 0, 0)
        return popupWindow
    }

    onFocusChangeListener = object : OnFocusChangeListener {
        private var mPopupWindow: PopupWindow? = null

        override fun onFocusChange(p0: View?, p1: Boolean) {
            if (p1) {
                activity?.hideKeyboard()
                mPopupWindow?.dismiss()
                mPopupWindow = showDropDown()
                clearFocus()
            }
        }
    }
}