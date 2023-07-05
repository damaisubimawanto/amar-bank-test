package com.damai.domain.models

/**
 * Created by damai007 on 05/July/2023
 */
data class MultiStepsModel(
    val svgIconRes: Int,
    val name: String,
    val state: RegistrationState,
    val isSelected: Boolean
)
