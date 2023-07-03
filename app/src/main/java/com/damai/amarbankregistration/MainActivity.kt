package com.damai.amarbankregistration

import android.os.Bundle
import com.damai.amarbankregistration.databinding.ActivityMainBinding
import com.damai.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResource: Int = R.layout.activity_main

    override val viewModel: MainViewModel
        get() = TODO("Not yet implemented")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFirstPage()
    }

    //region Private Functions
    private fun initFirstPage() {
        // TODO : Push SelfDataFragment into the FrameLayout by using AppNavigation
    }
    //endregion `Private Functions`
}