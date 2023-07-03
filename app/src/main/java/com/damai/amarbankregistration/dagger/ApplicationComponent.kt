package com.damai.amarbankregistration.dagger

import android.content.Context
import com.damai.amarbankregistration.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by damai007 on 03/July/2023
 */
@Singleton
@Component(
    modules = [
        RegistrationApiModule::class,
        AppNavigationModule::class,
        ViewModelBuilderModule::class,
        MainViewModelModule::class
    ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun inject(activity: MainActivity)
}