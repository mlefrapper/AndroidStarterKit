package com.mlefrapper.androidstarterkit.data.repository

import com.mlefrapper.androidstarterkit.data.model.Game
import com.mlefrapper.androidstarterkit.data.vo.Resource
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    fun getAllGames(): Flow<Resource<List<Game>>>
    fun getHotGames(): Flow<Resource<List<Game>>>
    fun getGameDetails(gameId: Long): Flow<Resource<Game>>
    fun getGameTrailer(gameId: Long): Flow<Resource<Game>>
    fun searchGame(query: String): Flow<Resource<List<Game>>>

    suspend fun setIsFavorites(isFavorites: Boolean, gameId: Long)
    fun getAllFavoritesGames(): Flow<Resource<List<Game>>>
}
