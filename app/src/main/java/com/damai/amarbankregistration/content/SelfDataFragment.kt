package com.damai.amarbankregistration.content

import com.damai.amarbankregistration.MainActivity
import com.damai.amarbankregistration.MainViewModel
import com.damai.amarbankregistration.R
import com.damai.amarbankregistration.databinding.FragmentSelfDataBinding
import com.damai.base.BaseFragment
import com.damai.base.extension.addOnTextChanged
import com.damai.base.extension.hideError
import com.damai.base.extension.hideKeyboard
import com.damai.base.extension.setCustomOnClickListener
import com.damai.base.extension.setupDatePicker
import com.damai.base.extension.setupDropDownAdapter
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

    override fun setupDaggerInjection() {
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

        etEducation.setupDropDownAdapter(
            activity = activity,
            list = resources.getStringArray(R.array.educations).toList()
        ) {
            tilEducation.hideError()
        }

        etDob.setupDatePicker {
            tilDob.hideError()
        }
    }

    override fun FragmentSelfDataBinding.setupListeners() {
        etNationalId.addOnTextChanged {
            if (it.isNotBlank()) {
                tilNationalId.hideError()
            }
        }
        etFullName.addOnTextChanged {
            if (it.isNotBlank()) {
                tilFullName.hideError()
            }
        }
        etBankAcountNumber.addOnTextChanged {
            if (it.isNotBlank()) {
                tilBankAccountNumber.hideError()
            }
        }

        btnSubmit.setCustomOnClickListener {
            if (isSelfDataMandatoryPassed()) {
                viewModel.triggerOnNextPage()
            }
        }
    }

    //region Private Functions
    private fun isSelfDataMandatoryPassed(): Boolean {
        with(binding) {
            val nationalId = etNationalId.text?.trim()?.toString().orEmpty()
            val fullName = etFullName.text?.trim()?.toString().orEmpty()
            val bankAccountNumber = etBankAcountNumber.text?.trim()?.toString().orEmpty()
            val education = etEducation.text?.trim()?.toString().orEmpty()
            val dateOfBirth = etDob.text?.trim()?.toString().orEmpty()

            viewModel.selfDataModel = SelfDataModel(
                nationalId = nationalId,
                fullName = fullName,
                bankAccountNumber = bankAccountNumber,
                education = education,
                dateOfBirth = dateOfBirth
            )

            /* Start the validation process. */
            var isError = false
            var errorMessage = ""
            if (dateOfBirth.isEmpty()) {
                isError = true
                tilDob.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_date_of_birth)
            }
            if (education.isEmpty()) {
                isError = true
                tilEducation.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_education)
            }
            if (bankAccountNumber.isEmpty()) {
                isError = true
                tilBankAccountNumber.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_bank_account_number)
            }
            if (fullName.isEmpty()) {
                isError = true
                tilFullName.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_full_name)
            }
            if (nationalId.isEmpty()) {
                isError = true
                tilNationalId.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_national_id)
            }

            /* Hide the keyboard and show the error message if there is any empty fields. */
            activity.hideKeyboard()
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