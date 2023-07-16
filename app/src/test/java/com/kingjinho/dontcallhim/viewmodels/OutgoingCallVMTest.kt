package com.kingjinho.dontcallhim.viewmodels

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.kingjinho.dontcallhim.database.FakeDatabase
import com.kingjinho.dontcallhim.repository.FakeAddNumberRepository
import com.kingjinho.dontcallhim.repository.FakeFetchNumbersRepository
import com.kingjinho.dontcallhim.usecase.FakeAddNumberUseCaseImpl
import com.kingjinho.dontcallhim.usecase.FakeFetchNumbersUseCaseImpl
import com.kingjinho.dontcallhim.usecase.add.AddNumberUseCase
import com.kingjinho.dontcallhim.usecase.fetch.FetchNumbersUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OutgoingCallVMTest {

    private lateinit var viewModel: OutgoingCallVM
    private lateinit var addNumberUseCase: AddNumberUseCase
    private lateinit var fetchNumbersUseCase: FetchNumbersUseCase

    @Before
    fun setup() {
        addNumberUseCase = FakeAddNumberUseCaseImpl(
            repo = FakeAddNumberRepository(database = FakeDatabase)
        )

        fetchNumbersUseCase = FakeFetchNumbersUseCaseImpl(
            repo = FakeFetchNumbersRepository(database = FakeDatabase)
        )

        viewModel = OutgoingCallVM(
            addNumberUseCase = addNumberUseCase,
            fetchNumbersUseCase = fetchNumbersUseCase
        )
    }

    @Test
    fun `addNumber() with param 01000000000 returns invalid phone number`() = runTest {
        viewModel.addNumber("01000000000").collectLatest {
            assertThat(it).isEqualTo(Result.Failure)
        }
    }

    @Test
    fun `addNumber() with param 0100000000 returns invalid phone number`() = runTest {
        val result = viewModel.addNumber("0100000000").first()
            assertThat(result).isEqualTo(Result.Failure)
    }

    @Test
    fun `addNumber() with param 010-0000-0000 returns invalid phone number`() = runTest {
        viewModel.addNumber("010-0000-0000").first()
        assertThat(FakeDatabase.dao.getExistingData("01000000000")).isTrue()
    }

}