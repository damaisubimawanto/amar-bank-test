package com.damai.amarbankregistration.dagger

import com.damai.amarbankregistration.MainActivity
import com.damai.amarbankregistration.content.DataReviewFragment
import com.damai.amarbankregistration.content.KtpAddressFragment
import com.damai.amarbankregistration.content.SelfDataFragment
import dagger.Subcomponent

/**
 * Created by damai007 on 04/July/2023
 */
/*@ActivityScope*/
@Subcomponent
interface RegisterComponent {

    /**
     * Factory that is used to create instances of this subcomponent
     */
    @Subcomponent.Factory
    interface Factory {
        fun create(): RegisterComponent
    }

    /**
     * This tells Dagger that MainActivity requests injection from RegisterComponent so that this
     * subcomponent graph needs to satisfy all the dependencies of the fields that MainActivity is
     * injecting.
     *
     * @param activity  the MainActivity instance
     */
    fun inject(activity: MainActivity)

    fun inject(fragment: SelfDataFragment)

    fun inject(fragment: KtpAddressFragment)

    fun inject(fragment: DataReviewFragment)
}