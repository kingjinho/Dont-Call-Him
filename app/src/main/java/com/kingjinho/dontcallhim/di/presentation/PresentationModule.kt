package com.kingjinho.dontcallhim.di.presentation

import com.kingjinho.dontcallhim.db.AppDatabase
import com.kingjinho.dontcallhim.repository.AddNumberRepo
import com.kingjinho.dontcallhim.repository.FetchNumbersRepo
import com.kingjinho.dontcallhim.usecase.add.AddNumberUseCase
import com.kingjinho.dontcallhim.usecase.add.AddNumberUseCaseImpl
import com.kingjinho.dontcallhim.usecase.fetch.FetchNumbersUseCase
import com.kingjinho.dontcallhim.usecase.fetch.FetchNumbersUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun getAddNumberRepo(db: AppDatabase): AddNumberRepo = AddNumberRepo(db)

    @Provides
    fun getFetchNumbersRepo(db: AppDatabase): FetchNumbersRepo = FetchNumbersRepo(db)

    @Provides
    fun getAddNumberUseCase(repo: AddNumberRepo): AddNumberUseCase = AddNumberUseCaseImpl(repo)

    @Provides
    fun getFetchNumbersUseCase(repo: FetchNumbersRepo): FetchNumbersUseCase =
        FetchNumbersUseCaseImpl(repo)
}