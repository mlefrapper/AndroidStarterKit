package com.mlefrapper.androidstarterkit.data.domain.usecase

import com.mlefrapper.androidstarterkit.data.model.Game
import com.mlefrapper.androidstarterkit.data.vo.Resource
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGames(): Flow<Resource<List<Game>>>
    fun getHotGames(): Flow<Resource<List<Game>>>
    fun getGameDetails(gameId: Long): Flow<Resource<Game>>
    fun searchGames(query: String): Flow<Resource<List<Game>>>
    fun getAllBookmarkedGames(): Flow<List<Game>>
    suspend fun setIsBookmarked(gameId: Long, isBookmarked: Boolean)
}
