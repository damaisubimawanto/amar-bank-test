package com.damai.amarbankregistration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.extension.asLiveData
import com.damai.base.util.Event
import com.damai.domain.models.KtpDataModel
import com.damai.domain.models.RegistrationState
import com.damai.domain.models.SelfDataModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by damai007 on 03/July/2023
 */
class MainViewModel @Inject constructor(
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    //region Live Data Variables
    private val _state = MutableLiveData<RegistrationState>()
    val state = _state.asLiveData()

    private val _nextPageTrigger = MutableLiveData<Event<Unit>>()
    val nextPageTrigger = _nextPageTrigger.asLiveData()

    private val _successRegisterData = MutableLiveData(false)
    val successRegisterData = _successRegisterData.asLiveData()
    //endregion `Live Data Variables`

    //region Data Variables
    var selfDataModel: SelfDataModel? = null
    var ktpDataModel: KtpDataModel? = null
    //endregion `Data Variables`

    //region Public Functions
    fun changeState(newState: RegistrationState) {
        newState.let(_state::postValue)
    }

    fun processRegistrationData() {
        viewModelScope.launch(dispatcher.io()) {
            /* Simulation for hitting to API. */
            delay(2_000L)
            _successRegisterData.postValue(true)
        }
    }
    //endregion `Public Functions`
}