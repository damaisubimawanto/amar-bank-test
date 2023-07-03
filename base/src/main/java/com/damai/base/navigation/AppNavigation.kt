package com.damai.base.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by damai007 on 03/July/2023
 */
interface AppNavigation {

    /**
     * Push a fragment to [FragmentManager] by clearing all the backstack
     */
    fun pushFragmentClearBackStack(
        fragmentManager: FragmentManager,
        @IdRes containerId: Int,
        fragment: Fragment
    )

    /**
     * Push a fragment to [FragmentManager] using [FragmentTransaction.add] if added into backstack previous
     * fragment will be resumed [Fragment.onResume]
     *
     * @param fragmentManager either [AppCompatActivity.getSupportFragmentManager] or [Fragment.getChildFragmentManager]
     * @param containerId view container id usually [FrameLayout]
     * @param fragment that will going to attach
     * @param fragmentTag will add to [FragmentTransaction.addToBackStack] if not empty
     */
    fun pushAddFragment(
        fragmentManager: FragmentManager,
        @IdRes containerId: Int,
        fragment: Fragment,
        fragmentTag: String? = null
    )
}