package com.damai.amarbankregistration.content

import com.damai.amarbankregistration.MainActivity
import com.damai.amarbankregistration.MainViewModel
import com.damai.amarbankregistration.R
import com.damai.amarbankregistration.databinding.FragmentDataReviewBinding
import com.damai.base.BaseFragment
import com.damai.base.extension.observe
import com.damai.base.extension.setCustomOnClickListener
import com.damai.base.extension.showToastMessage
import com.github.leandroborgesferreira.loadingbutton.animatedDrawables.ProgressType
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

    override fun setupDaggerInjection() {
        (context as MainActivity).registerComponent.inject(fragment = this)
    }

    override fun FragmentDataReviewBinding.viewInitialization() {
        viewModel.selfDataModel?.let { selfData ->
            selfDataModel = selfData
        }
        viewModel.ktpDataModel?.let { ktpData ->
            ktpDataModel = ktpData
        }
        executePendingBindings()

        btnSubmit.progressType = ProgressType.INDETERMINATE
    }

    override fun FragmentDataReviewBinding.setupListeners() {
        btnSubmit.setCustomOnClickListener {
            btnSubmit.startAnimation {
                viewModel.processRegistrationData()
            }
        }
    }

    override fun FragmentDataReviewBinding.setupObservers() {
        observe(viewModel.successRegisterData) { isSuccess ->
            if (isSuccess) {
                requireContext().showToastMessage(message = getString(R.string.successfully_submit))
                btnSubmit.revertAnimation {
                    btnSubmit.text = getString(R.string.thank_you)
                    btnSubmit.isEnabled = false
                    viewModel.proceedToFinishActivity()
                }
            }
        }
    }
}