package com.damai.amarbankregistration.dagger

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Scope
import kotlin.reflect.KClass

/**
 * Created by damai007 on 04/July/2023
 */

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope