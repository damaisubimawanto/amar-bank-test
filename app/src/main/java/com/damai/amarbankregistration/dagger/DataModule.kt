package com.damai.amarbankregistration.dagger

import com.damai.amarbankregistration.navigation.RegistrationFeatureApi
import com.damai.amarbankregistration.navigation.RegistrationFeatureApiImpl
import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.coroutines.DispatcherProviderImpl
import com.damai.base.navigation.AppNavigation
import com.damai.base.navigation.AppNavigationImpl
import com.damai.data.repo.MainRepositoryImpl
import com.damai.domain.repositories.MainRepository
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
    abstract fun bindAppNavigation(
        appNavigation: AppNavigationImpl
    ) : AppNavigation
}

@Module
abstract class DispatcherProviderModule {

    @Singleton
    @Binds
    abstract fun bindDispatcherProvider(
        dispatcherProvider: DispatcherProviderImpl
    ) : DispatcherProvider
}

@Module
abstract class MainRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindMainRepository(
        mainRepository: MainRepositoryImpl
    ) : MainRepository
}