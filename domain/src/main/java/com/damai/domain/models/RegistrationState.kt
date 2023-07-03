package com.damai.domain.models

/**
 * Created by damai007 on 03/July/2023
 */
sealed class RegistrationState {
    object SelfData : RegistrationState()

    object KtpData : RegistrationState()

    object DataReview : RegistrationState()
}
