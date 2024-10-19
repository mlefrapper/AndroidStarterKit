package com.mlefrapper.androidstarterkit.data.domain.usecase

import com.mlefrapper.androidstarterkit.data.model.Game
import com.mlefrapper.androidstarterkit.data.repository.GamesRepository
import com.mlefrapper.androidstarterkit.data.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor(
    private val gamesRepository: GamesRepository,
) : GameUseCase {

    override fun getAllGames(forceRefresh: Boolean): Flow<Resource<List<Game>>> {
        return gamesRepository.getAllGames(forceRefresh = forceRefresh)
    }

    override fun getHotGames(forceRefresh: Boolean): Flow<Resource<List<Game>>> {
        return gamesRepository.getHotGames(forceRefresh = forceRefresh)
    }

    override fun getGameDetails(gameId: Long): Flow<Resource<Game>> {
        return gamesRepository.getGameDetails(
            gameId = gameId,
        )
    }

    override fun getGameTrailer(gameId: Long): Flow<Resource<Game>> {
        return gamesRepository.getGameTrailer(
            gameId = gameId,
        )
    }

    override fun searchGames(query: String): Flow<Resource<List<Game>>> {
        return gamesRepository.searchGame(query)
    }

    override fun getAllBookmarkedGames(): Flow<List<Game>> {
        return gamesRepository.getAllBookmarkedGames()
    }

    override suspend fun setIsBookmarked(gameId: Long, isBookmarked: Boolean) {
        gamesRepository.setIsBookmarked(
            gameId = gameId,
            isBookmarked = isBookmarked,
        )
    }
}
