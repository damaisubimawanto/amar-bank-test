package com.damai.amarbankregistration

import android.app.Application
import com.damai.base.BaseViewModel
import com.damai.domain.models.KtpDataModel
import com.damai.domain.models.SelfDataModel

/**
 * Created by damai007 on 03/July/2023
 */
class MainViewModel(
    app: Application
) : BaseViewModel(app = app) {

    //region Data Variables
    var selfDataModel: SelfDataModel? = null
    var ktpDataModel: KtpDataModel? = null
    //endregion `Data Variables`
}