package com.damai.amarbankregistration

import androidx.activity.addCallback
import com.damai.amarbankregistration.adapter.MultiStepsAdapter
import com.damai.amarbankregistration.application.MyApplication
import com.damai.amarbankregistration.dagger.RegisterComponent
import com.damai.amarbankregistration.databinding.ActivityMainBinding
import com.damai.amarbankregistration.navigation.RegistrationFeatureApi
import com.damai.base.BaseActivity
import com.damai.base.extension.observe
import com.damai.base.util.EventObserver
import com.damai.domain.models.MultiStepsModel
import com.damai.domain.models.RegistrationState
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    //region Variables
    @Inject
    lateinit var registrationFeatureApi: RegistrationFeatureApi

    lateinit var registerComponent: RegisterComponent

    private lateinit var multiStepsAdapter: MultiStepsAdapter

    override val layoutResource: Int = R.layout.activity_main

    @Inject
    override lateinit var viewModel: MainViewModel
    //endregion `Variables`

    override fun setupDaggerInjection() {
        registerComponent = (applicationContext as MyApplication).appComponent
            .registerComponent()
            .create()
        registerComponent.inject(activity = this)
    }

    override fun ActivityMainBinding.viewInitialization() {
        with(rvMultiSteps) {
            multiStepsAdapter = MultiStepsAdapter(dataList = listOf())
            adapter = multiStepsAdapter
            generateMultiStepsData().let(multiStepsAdapter::setData)
        }
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
            val newDataList = when (it) {
                RegistrationState.SelfData -> {
                    multiStepsAdapter.getData().map { multiStepModel ->
                        multiStepModel.copy(
                            isSelected = when (multiStepModel.state) {
                                RegistrationState.SelfData -> true
                                else -> false
                            }
                        )
                    }
                }
                RegistrationState.KtpData -> {
                    multiStepsAdapter.getData().map { multiStepModel ->
                        multiStepModel.copy(
                            isSelected = when (multiStepModel.state) {
                                RegistrationState.SelfData, RegistrationState.KtpData -> true
                                else -> false
                            }
                        )
                    }
                }
                RegistrationState.DataReview -> {
                    multiStepsAdapter.getData().map { multiStepModel ->
                        multiStepModel.copy(
                            isSelected = true
                        )
                    }
                }
            }
            multiStepsAdapter.setData(newDataList = newDataList)
        }

        observe(viewModel.finishActivityLiveData) {
            finish()
        }
    }

    override fun ActivityMainBinding.onPreparationFinished() {
        initFirstPage()
        viewModel.getProvinceList()
    }

    //region Private Functions
    private fun generateMultiStepsData(): List<MultiStepsModel> {
        val selfStepData = MultiStepsModel(
            svgIconRes = R.drawable.ic_account,
            name = getString(R.string.step_account),
            state = RegistrationState.SelfData,
            isSelected = true
        )
        val ktpStepData = MultiStepsModel(
            svgIconRes = R.drawable.ic_house,
            name = getString(R.string.step_address),
            state = RegistrationState.KtpData,
            isSelected = false
        )
        val reviewStepData = MultiStepsModel(
            svgIconRes = R.drawable.ic_reference,
            name = getString(R.string.step_review),
            state = RegistrationState.DataReview,
            isSelected = false
        )
        val dataList = mutableListOf<MultiStepsModel>().apply {
            add(selfStepData)
            add(ktpStepData)
            add(reviewStepData)
        }
        return dataList.toList()
    }

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
        if (viewModel.isBackDisabled) return
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