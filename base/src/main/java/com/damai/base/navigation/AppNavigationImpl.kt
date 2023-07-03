package com.damai.base.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.damai.base.extension.slideTransition

/**
 * Created by damai007 on 03/July/2023
 */
class AppNavigationImpl : AppNavigation {

    override fun pushAddFragment(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        fragmentTag: String?
    ) = with(fragmentManager) {
        commit {
            slideTransition()
            fragmentTag.takeIf { it.isNullOrEmpty().not() }?.let { tag ->
                add(containerId, fragment, tag)
                addToBackStack(tag)
            } ?: add(containerId, fragment)
        }
    }
}