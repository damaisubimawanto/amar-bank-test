package com.damai.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

/**
 * Created by damai007 on 03/July/2023
 */
abstract class BaseActivity<VB: ViewDataBinding, VM: ViewModel> : AppCompatActivity() {

    abstract val layoutResource: Int
    abstract val viewModel: VM

    private var _binding: VB? = null
    protected val binding
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutResource)
        binding.setupListeners()
        binding.setupObservers()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    //region Optional implementation
    open fun VB.setupListeners() {}

    open fun VB.setupObservers() {}
    //endregion `Optional implementation`
}