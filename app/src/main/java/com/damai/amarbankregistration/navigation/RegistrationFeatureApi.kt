package com.damai.amarbankregistration.navigation

import androidx.fragment.app.FragmentActivity

/**
 * Created by damai007 on 03/July/2023
 */
interface RegistrationFeatureApi {

    fun navigateToSelfDataFragment(
        fragmentActivity: FragmentActivity,
        container: Int
    )

    fun navigateToKtpAddressFragment(
        fragmentActivity: FragmentActivity,
        container: Int
    )

    fun navigateToDataReviewFragment(
        fragmentActivity: FragmentActivity,
        container: Int
    )
}