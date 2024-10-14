package com.mlefrapper.androidstarterkit.domain.usecase

import com.mlefrapper.androidstarterkit.domain.model.Game
import com.mlefrapper.androidstarterkit.domain.repository.GamesRepository
import com.mlefrapper.core.vo.Resource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllGamesUseCase @Inject constructor(
    private val repo: GamesRepository,
) {
    fun execute(): Flow<Resource<List<Game>>> {
        return repo.getAllGames()
    }
}
