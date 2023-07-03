package com.damai.amarbankregistration.dagger

import androidx.lifecycle.ViewModel
import com.damai.amarbankregistration.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by damai007 on 03/July/2023
 */
@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel
}