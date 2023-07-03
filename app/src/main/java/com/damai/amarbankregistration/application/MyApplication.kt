package com.damai.amarbankregistration.application

import android.app.Application
import com.damai.amarbankregistration.dagger.DaggerApplicationComponent

/**
 * Created by damai007 on 03/July/2023
 */
class MyApplication : Application() {

    val appComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}