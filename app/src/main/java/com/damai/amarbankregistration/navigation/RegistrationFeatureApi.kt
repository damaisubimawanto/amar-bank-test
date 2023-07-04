package com.damai.amarbankregistration.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity

/**
 * Created by damai007 on 03/July/2023
 */
interface RegistrationFeatureApi {

    fun navigateToSelfDataFragment(
        fragmentActivity: FragmentActivity,
        @IdRes container: Int
    )

    fun navigateToKtpAddressFragment(
        fragmentActivity: FragmentActivity,
        @IdRes container: Int
    )

    fun navigateToDataReviewFragment(
        fragmentActivity: FragmentActivity,
        @IdRes container: Int
    )
}