package com.damai.amarbankregistration

import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.damai.amarbankregistration.application.MyApplication
import com.damai.amarbankregistration.databinding.ActivityMainBinding
import com.damai.amarbankregistration.navigation.RegistrationFeatureApi
import com.damai.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    //region Variables
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var registrationFeatureApi: RegistrationFeatureApi
    //endregion `Variables`

    override val layoutResource: Int = R.layout.activity_main

    override val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        initFirstPage()
    }

    override fun ActivityMainBinding.setupListeners() {
        onBackPressedDispatcher.addCallback(lifecycleOwner) {
            handleBackPress()
        }
    }

    //region Private Functions
    private fun initFirstPage() {
        registrationFeatureApi.navigateToSelfDataFragment(
            fragmentActivity = this,
            container = R.id.flMainContent
        )
    }

    private fun handleBackPress() {
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