package com.mlefrapper.androidstarterkit.data

import com.mlefrapper.androidstarterkit.data.local.LocalDataSource
import com.mlefrapper.androidstarterkit.data.local.entity.GameEntity
import com.mlefrapper.androidstarterkit.data.remote.RemoteDataSource
import com.mlefrapper.androidstarterkit.data.remote.model.GamesResponseDto
import com.mlefrapper.androidstarterkit.domain.model.Game
import com.mlefrapper.androidstarterkit.domain.repository.GamesRepository
import com.mlefrapper.androidstarterkit.utils.Range
import com.mlefrapper.androidstarterkit.utils.getDateRange
import com.mlefrapper.core.vo.Resource
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GamesDataStore @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : GamesRepository {

    override fun getAllGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, GamesResponseDto>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGames()
                    .map { games ->
                        games.map {
                            Game(it)
                        }
                    }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): ApiResponse<GamesResponseDto> {
                return remoteDataSource.getAllGames()
            }

            override suspend fun saveCallResult(data: GamesResponseDto) {
                localDataSource.insertGames(
                    data.results?.map {
                        GameEntity(it)
                    }.orEmpty(),
                )
            }
        }.asFlow()

    override fun getHotGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, GamesResponseDto>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource
                    .getHotGames()
                    .map { games ->
                        games.map {
                            Game(it)
                        }
                    }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): ApiResponse<GamesResponseDto> {
                return remoteDataSource.getAllGames(
                    dates = getDateRange(
                        range = Range.MONTH,
                        isPast = true
                    ),
                    ordering = ORDERING_RATING,
                    page = FIRST_PAGE,
                    pageSize = PAGE_SIZE,
                )
            }

            override suspend fun saveCallResult(data: GamesResponseDto) {
                localDataSource.insertGames(
                    data.results?.map {
                        GameEntity(it)
                    }.orEmpty(),
                )
            }
        }.asFlow()

    override fun getGameDetails(id: Long): Flow<Resource<Game>> {
        TODO("Not yet implemented")
    }

    override fun getGameTrailer(id: Long): Flow<Resource<Game>> {
        TODO("Not yet implemented")
    }

    override fun searchGame(query: String): Flow<Resource<List<Game>>> {
        TODO("Not yet implemented")
    }

    override suspend fun setIsFavorites(isFavorites: Boolean, id: Long) {
        TODO("Not yet implemented")
    }

    override fun getAllFavoritesGames(): Flow<Resource<List<Game>>> {
        TODO("Not yet implemented")
    }

    companion object {
        private const val FIRST_PAGE = 1
        private const val PAGE_SIZE = 10
        private const val ORDERING_RATING = "-rating"
    }
}
