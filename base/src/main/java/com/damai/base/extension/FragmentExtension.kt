package com.damai.base.extension

import androidx.fragment.app.FragmentTransaction
import com.damai.base.R

/**
 * Created by damai007 on 03/July/2023
 */

fun FragmentTransaction.slideTransition() {
    setCustomAnimations(
        R.anim.enter_from_right,
        R.anim.exit_to_left,
        R.anim.enter_from_left,
        R.anim.exit_to_right
    )
}