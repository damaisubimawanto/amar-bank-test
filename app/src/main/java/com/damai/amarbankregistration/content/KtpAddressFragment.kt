package com.damai.amarbankregistration.content

import android.content.Context
import com.damai.amarbankregistration.MainActivity
import com.damai.amarbankregistration.MainViewModel
import com.damai.amarbankregistration.R
import com.damai.amarbankregistration.databinding.FragmentKtpAddressBinding
import com.damai.base.BaseFragment
import com.damai.base.extension.addOnTextChanged
import com.damai.base.extension.hideError
import com.damai.base.extension.hideKeyboard
import com.damai.base.extension.setCustomOnClickListener
import com.damai.base.extension.setupDropDownAdapter
import com.damai.base.extension.showDefaultSnackBar
import com.damai.domain.models.KtpDataModel
import javax.inject.Inject

/**
 * Created by damai007 on 03/July/2023
 */
class KtpAddressFragment : BaseFragment<FragmentKtpAddressBinding, MainViewModel>() {

    //region Variables
    override val layoutResource: Int = R.layout.fragment_ktp_address

    @Inject
    override lateinit var viewModel: MainViewModel
    //endregion `Variables`

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).registerComponent.inject(fragment = this)
    }

    override fun FragmentKtpAddressBinding.viewInitialization() {
        etHousingType.setupDropDownAdapter(
            activity = activity,
            list = resources.getStringArray(R.array.housing_types).toList()
        ) {
            tilHousingType.hideError()
        }

        viewModel.provinceListLiveData.value?.let {
            etProvince.setupDropDownAdapter(
                activity = activity,
                list = it
            ) {
                tilProvince.hideError()
            }
        }
    }

    override fun FragmentKtpAddressBinding.setupListeners() {
        etDomicile.addOnTextChanged {
            if (it.isNotBlank()) {
                tilDomicile.hideError()
            }
        }
        etHouseNumber.addOnTextChanged {
            if (it.isNotBlank()) {
                tilHouseNumber.hideError()
            }
        }

        btnSubmit.setCustomOnClickListener {
            if (isKtpDataMandatoryPassed()) {
                viewModel.triggerOnNextPage()
            }
        }
    }

    //region Private Functions
    private fun isKtpDataMandatoryPassed(): Boolean {
        with(binding) {
            val domicileAddress = etDomicile.text?.trim()?.toString().orEmpty()
            val housingType = etHousingType.text?.trim()?.toString().orEmpty()
            val houseNumber = etHouseNumber.text?.trim()?.toString().orEmpty()
            val province = etProvince.text?.trim()?.toString().orEmpty()

            viewModel.ktpDataModel = KtpDataModel(
                domicileAddress = domicileAddress,
                houseType = housingType,
                houseNumber = houseNumber,
                province = province
            )

            /* Start the validation process. */
            var isError = false
            var errorMessage = ""
            if (province.isEmpty()) {
                isError = true
                tilProvince.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_province)
            }
            if (houseNumber.isEmpty()) {
                isError = true
                tilHouseNumber.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_house_number)
            }
            if (housingType.isEmpty()) {
                isError = true
                tilHousingType.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_housing_type)
            }
            if (domicileAddress.isEmpty()) {
                isError = true
                tilDomicile.error = getString(R.string.error_cannot_be_empty)
                errorMessage = getString(R.string.error_domicile_address)
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