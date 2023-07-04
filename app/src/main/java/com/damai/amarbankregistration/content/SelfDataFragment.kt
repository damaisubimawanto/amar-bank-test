package com.damai.amarbankregistration.content

import android.content.Context
import com.damai.amarbankregistration.MainActivity
import com.damai.amarbankregistration.MainViewModel
import com.damai.amarbankregistration.R
import com.damai.amarbankregistration.databinding.FragmentSelfDataBinding
import com.damai.base.BaseFragment
import com.damai.base.extension.setCustomOnClickListener
import com.damai.base.extension.showDefaultSnackBar
import com.damai.domain.models.SelfDataModel
import javax.inject.Inject

/**
 * Created by damai007 on 03/July/2023
 */
class SelfDataFragment : BaseFragment<FragmentSelfDataBinding, MainViewModel>() {

    //region Variables
    override val layoutResource: Int = R.layout.fragment_self_data

    @Inject
    override lateinit var viewModel: MainViewModel
    //endregion `Variables`

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).registerComponent.inject(fragment = this)
    }

    override fun FragmentSelfDataBinding.viewInitialization() {
        viewModel.selfDataModel?.let { data ->
            etNationalId.setText(data.nationalId)
            etFullName.setText(data.fullName)
            etBankAcountNumber.setText(data.bankAccountNumber)
            etEducation.setText(data.education)
            etDob.setText(data.dateOfBirth)
        }
    }

    override fun FragmentSelfDataBinding.setupListeners() {
        btnSubmit.setCustomOnClickListener {
            if (isSelfDataMandatoryPassed()) {
                viewModel.triggerOnNextPage()
            }
        }
    }

    //region Private Functions
    private fun isSelfDataMandatoryPassed(): Boolean {
        with(binding) {
            val nationalId = etNationalId.text?.toString()?.trim().orEmpty()
            val fullName = etFullName.text?.toString()?.trim().orEmpty()
            val bankAccountNumber = etBankAcountNumber.text?.toString()?.trim().orEmpty()
            val education = etEducation.text?.toString()?.trim().orEmpty()
            val dateOfBirth = etDob.text?.toString()?.trim().orEmpty()

            viewModel.selfDataModel = SelfDataModel(
                nationalId = nationalId,
                fullName = fullName,
                bankAccountNumber = bankAccountNumber,
                education = education,
                dateOfBirth = dateOfBirth
            )

            var isError = false
            var errorMessage = ""
            if (dateOfBirth.isEmpty()) {
                isError = true
                tilDob.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_date_of_birth)
            } else {
                tilDob.error = null
            }

            if (education.isEmpty()) {
                isError = true
                tilEducation.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_education)
            } else {
                tilEducation.error = null
            }

            if (bankAccountNumber.isEmpty()) {
                isError = true
                tilBankAccountNumber.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_bank_account_number)
            } else {
                tilBankAccountNumber.error = null
            }

            if (fullName.isEmpty()) {
                isError = true
                tilFullName.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_full_name)
            } else {
                tilFullName.error = null
            }

            if (nationalId.isEmpty()) {
                isError = true
                tilNationalId.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_national_id)
            } else {
                tilNationalId.error = null
            }

            return if (isError) {
                svMainView.showDefaultSnackBar(message = errorMessage)
                false
            } else {
                true
            }
        }
    }
    //endregion `Private Functions`
}