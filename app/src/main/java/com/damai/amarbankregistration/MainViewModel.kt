package com.damai.amarbankregistration

import androidx.lifecycle.ViewModel
import com.damai.domain.models.KtpDataModel
import com.damai.domain.models.SelfDataModel
import javax.inject.Inject

/**
 * Created by damai007 on 03/July/2023
 */
class MainViewModel @Inject constructor() : ViewModel() {

    //region Data Variables
    var selfDataModel: SelfDataModel? = null
    var ktpDataModel: KtpDataModel? = null
    //endregion `Data Variables`
}