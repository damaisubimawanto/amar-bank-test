package com.damai.amarbankregistration

import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.damai.amarbankregistration.utils.CoroutineTestRule
import com.damai.amarbankregistration.utils.InstantExecutorExtension
import com.damai.base.network.Resource
import com.damai.base.util.Event
import com.damai.data.response.ProvinceResponse
import com.damai.domain.models.ProvinceListModel
import com.damai.domain.models.RegistrationState
import com.damai.domain.repositories.MainRepository
import com.damai.domain.usecases.ProvinceUseCase
import com.jraska.livedata.test
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.excludeRecords
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import retrofit2.Response

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
    private val mainRepositoty = mockk<MainRepository>()

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
        val newState: RegistrationState = mockk(relaxed = true)
        viewModel.changeState(newState = newState)

        coVerify(exactly = 1) {
            stateObserver.onChanged(any())
        }

        val testObserver = viewModel.state.test()
            .assertHasValue()
        val content = testObserver.value()
        assertEquals(newState, content)

        excludeRecords { viewModel.state.observeForever(testObserver) }

        confirmVerified(stateObserver)
    }

    @Test
    fun `trigger on next page should update live data`() {
        viewModel.triggerOnNextPage()

        coVerify(exactly = 1) {
            nextPageTriggerObserver.onChanged(any())
        }

        confirmVerified(nextPageTriggerObserver)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get province list should update live data`() = runTest {
        val response: Response<ProvinceResponse> = mockk()
        val body: ProvinceResponse = mockk()
        val data: List<ProvinceResponse.ProvinceDetail> = mockk()

        val bodyConverted: ProvinceListModel = mockk()
        val dataConverted: List<String> = mockk()
        val flowResponse = flow<Resource<ProvinceListModel>> {
            emit(Resource.Success(bodyConverted))
        }

        every { response.isSuccessful } returns true
        every { response.code() } returns 200
        every { response.body() } returns body
        every { body.data } returns data
        every { bodyConverted.data } returns dataConverted
        every { runBlocking { provinceUseCase() } } returns flowResponse
        every { mainRepositoty.getProvinceList() } returns flowResponse

        provinceUseCase().collectLatest {
            assertTrue(it is Resource.Success)
        }
    }
}