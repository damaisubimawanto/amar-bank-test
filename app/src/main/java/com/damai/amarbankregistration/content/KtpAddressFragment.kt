package com.damai.amarbankregistration.content

import android.content.Context
import com.damai.amarbankregistration.MainActivity
import com.damai.amarbankregistration.MainViewModel
import com.damai.amarbankregistration.R
import com.damai.amarbankregistration.databinding.FragmentKtpAddressBinding
import com.damai.base.BaseFragment
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
        // TODO("Not yet implemented")
    }

    //region Private Functions
    private fun isKtpDataMandatoryPassed(): Boolean {
        with(binding) {

            return true
        }
    }
    //endregion `Private Functions`
}