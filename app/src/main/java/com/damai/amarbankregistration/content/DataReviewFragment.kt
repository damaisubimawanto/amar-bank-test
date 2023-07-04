package com.damai.amarbankregistration.content

import android.content.Context
import com.damai.amarbankregistration.MainActivity
import com.damai.amarbankregistration.MainViewModel
import com.damai.amarbankregistration.R
import com.damai.amarbankregistration.databinding.FragmentDataReviewBinding
import com.damai.base.BaseFragment
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
        // TODO("Not yet implemented")
    }

    override fun FragmentDataReviewBinding.onPreparationFinished() {
        // TODO("Not yet implemented")
    }
}