package com.damai.amarbankregistration

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.damai.amarbankregistration.application.MyApplication
import com.damai.amarbankregistration.dagger.RegisterComponent
import com.damai.amarbankregistration.databinding.ActivityMainBinding
import com.damai.amarbankregistration.navigation.RegistrationFeatureApi
import com.damai.base.BaseActivity
import com.damai.base.extension.observe
import com.damai.base.util.EventObserver
import com.damai.domain.models.RegistrationState
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    //region Variables
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var registrationFeatureApi: RegistrationFeatureApi

    lateinit var registerComponent: RegisterComponent

    override val layoutResource: Int = R.layout.activity_main

    override val viewModel: MainViewModel by viewModels { viewModelFactory }
    //endregion `Variables`

    override fun onCreate(savedInstanceState: Bundle?) {
        registerComponent = (applicationContext as MyApplication).appComponent
            .registerComponent()
            .create()
        registerComponent.inject(activity = this)
        super.onCreate(savedInstanceState)
        initFirstPage()
    }

    override fun ActivityMainBinding.setupListeners() {
        onBackPressedDispatcher.addCallback(lifecycleOwner) {
            handleBackPressed()
        }
    }

    override fun ActivityMainBinding.setupObservers() {
        observe(viewModel.nextPageTrigger, EventObserver {
            when (viewModel.state.value) {
                RegistrationState.SelfData -> openSecondPage()
                RegistrationState.KtpData -> openThirdPage()
                RegistrationState.DataReview -> viewModel.processRegistrationData()
                else -> Unit
            }
        })

        observe(viewModel.state) {
            when (viewModel.state.value) {
                RegistrationState.SelfData -> {

                }
                RegistrationState.KtpData -> {

                }
                RegistrationState.DataReview -> {

                }
                else -> Unit
            }
        }

        observe(viewModel.successRegisterData) { isSuccess ->
            if (isSuccess) {

            }
        }
    }

    //region Private Functions
    private fun initFirstPage() {
        registrationFeatureApi.navigateToSelfDataFragment(
            fragmentActivity = this,
            container = binding.flMainContent.id
        )
        viewModel.changeState(newState = RegistrationState.SelfData)
    }

    private fun openSecondPage() {
        registrationFeatureApi.navigateToKtpAddressFragment(
            fragmentActivity = this,
            container = binding.flMainContent.id
        )
        viewModel.changeState(newState = RegistrationState.KtpData)
    }

    private fun openThirdPage() {
        registrationFeatureApi.navigateToDataReviewFragment(
            fragmentActivity = this,
            container = binding.flMainContent.id
        )
        viewModel.changeState(newState = RegistrationState.DataReview)
    }

    private fun handleBackPressed() {
        with(supportFragmentManager) {
            if (backStackEntryCount == 0) {
                finish()
            } else {
                popBackStack()
            }
        }
    }
    //endregion `Private Functions`
}