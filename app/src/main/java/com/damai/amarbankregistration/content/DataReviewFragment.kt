package com.damai.amarbankregistration.content

import android.content.Context
import com.damai.amarbankregistration.MainActivity
import com.damai.amarbankregistration.MainViewModel
import com.damai.amarbankregistration.R
import com.damai.amarbankregistration.databinding.FragmentDataReviewBinding
import com.damai.base.BaseFragment
import com.damai.base.extension.setCustomOnClickListener
import javax.inject.Inject

/**
 * Created by damai007 on 03/July/2023
 */
class DataReviewFragment : BaseFragment<FragmentDataReviewBinding, MainViewModel>() {

    //region Variables
    override val layoutResource: Int = R.layout.fragment_data_review

    @Inject
    override lateinit var viewModel: MainViewModel
    //endregion `Variables`

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).registerComponent.inject(fragment = this)
    }

    override fun FragmentDataReviewBinding.viewInitialization() {
        viewModel.selfDataModel?.let { selfData ->
            binding.selfDataModel = selfData
        }
        viewModel.ktpDataModel?.let { ktpData ->
            binding.ktpDataModel = ktpData
        }
        binding.executePendingBindings()
    }

    override fun FragmentDataReviewBinding.setupListeners() {
        btnSubmit.setCustomOnClickListener {
            viewModel.processRegistrationData()
        }
    }
}