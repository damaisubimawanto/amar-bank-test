package com.damai.amarbankregistration

import android.os.Bundle
import androidx.activity.addCallback
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
    lateinit var registrationFeatureApi: RegistrationFeatureApi

    lateinit var registerComponent: RegisterComponent

    override val layoutResource: Int = R.layout.activity_main

    @Inject
    override lateinit var viewModel: MainViewModel
    //endregion `Variables`

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFirstPage()
        viewModel.getProvinceList()
    }

    override fun setupDaggerInjection() {
        registerComponent = (applicationContext as MyApplication).appComponent
            .registerComponent()
            .create()
        registerComponent.inject(activity = this)
    }

    override fun ActivityMainBinding.setupListeners() {
        onBackPressedDispatcher.addCallback(this@MainActivity) {
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
            when (it) {
                RegistrationState.SelfData -> {

                }
                RegistrationState.KtpData -> {

                }
                RegistrationState.DataReview -> {

                }
            }
        }

        observe(viewModel.successRegisterData) { isSuccess ->
            if (isSuccess) {
                finish()
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
                when (viewModel.state.value) {
                    RegistrationState.DataReview -> {
                        viewModel.changeState(newState = RegistrationState.KtpData)
                    }
                    RegistrationState.KtpData -> {
                        viewModel.changeState(newState = RegistrationState.SelfData)
                    }
                    else -> Unit
                }
                popBackStack()
            }
        }
    }
    //endregion `Private Functions`
}