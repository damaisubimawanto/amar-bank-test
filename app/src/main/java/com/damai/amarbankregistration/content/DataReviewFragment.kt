package com.damai.amarbankregistration.content

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.damai.amarbankregistration.MainViewModel
import com.damai.amarbankregistration.R
import com.damai.amarbankregistration.databinding.FragmentDataReviewBinding
import com.damai.base.BaseFragment
import javax.inject.Inject

/**
 * Created by damai007 on 03/July/2023
 */
class DataReviewFragment : BaseFragment<FragmentDataReviewBinding, MainViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val layoutResource: Int = R.layout.fragment_data_review

    override val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun FragmentDataReviewBinding.viewInitialization() {
        // TODO("Not yet implemented")
    }

    override fun FragmentDataReviewBinding.onPreparationFinished() {
        // TODO("Not yet implemented")
    }
}