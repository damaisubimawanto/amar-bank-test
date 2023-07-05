package com.damai.amarbankregistration

import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.damai.amarbankregistration.utils.CoroutineTestRule
import com.damai.amarbankregistration.utils.InstantExecutorExtension
import com.damai.base.util.Event
import com.damai.domain.models.RegistrationState
import com.damai.domain.usecases.ProvinceUseCase
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith

/**
 * Created by damai007 on 05/July/2023
 */
@RunWith(AndroidJUnit4::class)
@ExtendWith(InstantExecutorExtension::class)
@MediumTest
class MainViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private val provinceUseCase = mockk<ProvinceUseCase>()

    private val viewModel = MainViewModel(
        provinceUseCase = provinceUseCase,
        dispatcher = coroutineRule.dispatcherProvider
    )

    private val stateObserver = mockk<Observer<RegistrationState>>(relaxed = true)
    private val nextPageTriggerObserver = mockk<Observer<Event<Unit>>>(relaxed = true)
    private val successRegisterDataObserver = mockk<Observer<Boolean>>(relaxed = true)
    private val finishActivityObserver = mockk<Observer<Unit>>(relaxed = true)
    private val provinceListObserver = mockk<Observer<List<String>>>(relaxed = true)

    @Before
    fun setup() {
        viewModel.state.observeForever(stateObserver)
        viewModel.nextPageTrigger.observeForever(nextPageTriggerObserver)
        viewModel.successRegisterData.observeForever(successRegisterDataObserver)
        viewModel.finishActivityLiveData.observeForever(finishActivityObserver)
        viewModel.provinceListLiveData.observeForever(provinceListObserver)
    }

    @After
    fun cleanUp() {
        viewModel.state.removeObserver(stateObserver)
        viewModel.nextPageTrigger.removeObserver(nextPageTriggerObserver)
        viewModel.successRegisterData.removeObserver(successRegisterDataObserver)
        viewModel.finishActivityLiveData.removeObserver(finishActivityObserver)
        viewModel.provinceListLiveData.removeObserver(provinceListObserver)
    }

    @Test
    fun `change state should update live data`() {
        viewModel.changeState(newState = RegistrationState.KtpData)

        val state = viewModel.state.value
        assertEquals(RegistrationState.KtpData, state)
    }
}