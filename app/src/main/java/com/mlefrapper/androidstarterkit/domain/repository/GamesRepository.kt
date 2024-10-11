package com.mlefrapper.androidstarterkit.domain.repository

import com.mlefrapper.androidstarterkit.domain.model.Game
import com.mlefrapper.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    fun getAllGames(): Flow<Resource<List<Game>>>
    fun getHotGames(): Flow<Resource<List<Game>>>
    fun getGameDetails(id: Long): Flow<Resource<Game>>
    fun getGameTrailer(id: Long): Flow<Resource<Game>>
    fun searchGame(query: String): Flow<Resource<List<Game>>>

    suspend fun setIsFavorites(isFavorites: Boolean, id: Long)
    fun getAllFavoritesGames(): Flow<Resource<List<Game>>>
}
