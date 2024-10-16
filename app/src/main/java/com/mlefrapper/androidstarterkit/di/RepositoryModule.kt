package com.mlefrapper.androidstarterkit.di

import com.mlefrapper.androidstarterkit.data.GamesDataStore
import com.mlefrapper.androidstarterkit.data.domain.usecase.GameInteractor
import com.mlefrapper.androidstarterkit.data.domain.usecase.GameUseCase
import com.mlefrapper.androidstarterkit.data.local.room.LocalDataSource
import com.mlefrapper.androidstarterkit.data.local.room.LocalDataSourceImpl
import com.mlefrapper.androidstarterkit.data.repository.GamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl,
    ): LocalDataSource

    @Binds
    @Singleton
    abstract fun bindRepository(
        gamesDataStore: GamesDataStore,
    ): GamesRepository

    @Binds
    @Singleton
    abstract fun bindGameUseCase(
        gameInteractor: GameInteractor,
    ): GameUseCase
}
