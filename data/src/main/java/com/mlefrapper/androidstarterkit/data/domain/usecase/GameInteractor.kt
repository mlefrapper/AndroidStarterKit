package com.mlefrapper.androidstarterkit.data.domain.usecase

import com.mlefrapper.androidstarterkit.data.model.Game
import com.mlefrapper.androidstarterkit.data.repository.GamesRepository
import com.mlefrapper.androidstarterkit.data.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor(
    private val gamesRepository: GamesRepository,
) : GameUseCase {

    override fun getAllGames(): Flow<Resource<List<Game>>> {
        return gamesRepository.getAllGames()
    }

    override fun getHotGames(): Flow<Resource<List<Game>>> {
        return gamesRepository.getHotGames()
    }
}
