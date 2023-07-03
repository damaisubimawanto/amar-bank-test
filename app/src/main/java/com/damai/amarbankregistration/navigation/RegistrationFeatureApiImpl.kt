package com.damai.amarbankregistration.navigation

import androidx.fragment.app.FragmentActivity
import com.damai.amarbankregistration.content.DataReviewFragment
import com.damai.amarbankregistration.content.KtpAddressFragment
import com.damai.amarbankregistration.content.SelfDataFragment
import com.damai.base.navigation.AppNavigation

/**
 * Created by damai007 on 03/July/2023
 */
class RegistrationFeatureApiImpl(
    private val appNavigation: AppNavigation
) : RegistrationFeatureApi {

    override fun navigateToSelfDataFragment(fragmentActivity: FragmentActivity, container: Int) {
        appNavigation.pushAddFragment(
            fragmentManager = fragmentActivity.supportFragmentManager,
            containerId = container,
            fragment = SelfDataFragment(),
            fragmentTag = SelfDataFragment::class.java.simpleName
        )
    }

    override fun navigateToKtpAddressFragment(fragmentActivity: FragmentActivity, container: Int) {
        appNavigation.pushAddFragment(
            fragmentManager = fragmentActivity.supportFragmentManager,
            containerId = container,
            fragment = KtpAddressFragment(),
            fragmentTag = KtpAddressFragment::class.java.simpleName
        )
    }

    override fun navigateToDataReviewFragment(fragmentActivity: FragmentActivity, container: Int) {
        appNavigation.pushAddFragment(
            fragmentManager = fragmentActivity.supportFragmentManager,
            containerId = container,
            fragment = DataReviewFragment(),
            fragmentTag = DataReviewFragment::class.java.simpleName
        )
    }
}