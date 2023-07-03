package com.damai.amarbankregistration.dagger

import com.damai.amarbankregistration.navigation.RegistrationFeatureApi
import com.damai.amarbankregistration.navigation.RegistrationFeatureApiImpl
import com.damai.base.navigation.AppNavigation
import com.damai.base.navigation.AppNavigationImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by damai007 on 03/July/2023
 */
@Module
abstract class RegistrationApiModule {

    @Singleton
    @Binds
    abstract fun bindRegistrationApi(
        registrationApi: RegistrationFeatureApiImpl
    ) : RegistrationFeatureApi
}

@Module
abstract class AppNavigationModule {

    @Singleton
    @Binds
    abstract fun bindAppNavigation(appNavigation: AppNavigationImpl) : AppNavigation
}