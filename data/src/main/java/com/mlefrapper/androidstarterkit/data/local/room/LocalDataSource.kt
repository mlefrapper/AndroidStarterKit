package com.mlefrapper.androidstarterkit.data.local.room

import com.mlefrapper.androidstarterkit.data.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllGames(): Flow<List<GameEntity>>
    fun getHotGames(): Flow<List<GameEntity>>
    fun searchGames(query: String): Flow<List<GameEntity>>
    fun getGameDetail(id: Long): Flow<GameEntity?>
    suspend fun setIsBookmarked(gameId: Long, isBookmarked: Boolean)
    fun getAllFavoriteGames(): Flow<List<GameEntity>>
    suspend fun insertGames(games: List<GameEntity>)
    suspend fun updateGameDescription(id: Long, description: String)
    suspend fun updateGameTrailer(id: Long, trailerUrl: String)
}
