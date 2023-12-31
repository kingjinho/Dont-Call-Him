package com.kingjinho.dontcallhim.di.presentation

import com.kingjinho.dontcallhim.db.AppDatabase
import com.kingjinho.dontcallhim.repository.AddNumberRepo
import com.kingjinho.dontcallhim.repository.FetchNumbersRepo
import com.kingjinho.dontcallhim.usecase.add.AddNumberUseCase
import com.kingjinho.dontcallhim.usecase.add.AddNumberUseCaseImpl
import com.kingjinho.dontcallhim.usecase.fetch.FetchNumbersUseCase
import com.kingjinho.dontcallhim.usecase.fetch.FetchNumbersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(value = [ViewModelComponent::class])
abstract class ViewModelModule {

    companion object {
        @Provides
        fun fetchNumberRepo(database: AppDatabase) = FetchNumbersRepo(database)

        @Provides
        fun addNumberRepo(database: AppDatabase) = AddNumberRepo(database)
    }

    @Binds
    abstract fun fetchNumberUseCase(impl: FetchNumbersUseCaseImpl): FetchNumbersUseCase

    @Binds
    abstract fun addNumberUseCase(impl: AddNumberUseCaseImpl): AddNumberUseCase
}