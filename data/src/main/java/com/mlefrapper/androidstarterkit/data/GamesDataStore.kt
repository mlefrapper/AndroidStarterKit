package com.mlefrapper.androidstarterkit.data

import com.mlefrapper.androidstarterkit.data.local.entity.GameEntity
import com.mlefrapper.androidstarterkit.data.local.room.LocalDataSource
import com.mlefrapper.androidstarterkit.data.model.Game
import com.mlefrapper.androidstarterkit.data.remote.RemoteDataSource
import com.mlefrapper.androidstarterkit.data.remote.model.GameItemDto
import com.mlefrapper.androidstarterkit.data.remote.model.GameTrailerResponseDto
import com.mlefrapper.androidstarterkit.data.remote.model.GamesResponseDto
import com.mlefrapper.androidstarterkit.data.repository.GamesRepository
import com.mlefrapper.androidstarterkit.data.utils.Range
import com.mlefrapper.androidstarterkit.data.utils.getDateRange
import com.mlefrapper.androidstarterkit.data.vo.Resource
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GamesDataStore @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : GamesRepository {

    override fun getAllGames(forceRefresh: Boolean): Flow<Resource<List<Game>>> =
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
                data.isNullOrEmpty() || forceRefresh

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

    override fun getHotGames(forceRefresh: Boolean): Flow<Resource<List<Game>>> =
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
                data.isNullOrEmpty() || forceRefresh

            override suspend fun createCall(): ApiResponse<GamesResponseDto> {
                return remoteDataSource.getAllGames(
                    dates = getDateRange(
                        range = Range.MONTH,
                        isPast = true,
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

    override fun getGameDetails(gameId: Long): Flow<Resource<Game>> {
        return object : NetworkBoundResource<Game, GameItemDto>() {
            override fun loadFromDB(): Flow<Game> {
                return localDataSource
                    .getGameDetail(gameId)
                    .map { gameEntity ->
                        Game(gameEntity)
                    }
            }

            override fun shouldFetch(data: Game?): Boolean {
                return data?.description.isNullOrEmpty()
            }

            override suspend fun createCall(): ApiResponse<GameItemDto> {
                return remoteDataSource
                    .getGameDetails(gameId)
            }

            override suspend fun saveCallResult(data: GameItemDto) {
                data.id?.let { id ->
                    localDataSource.updateGameDescription(
                        id = id,
                        description = data.description.orEmpty(),
                    )
                }
            }
        }.asFlow()
    }

    override fun getGameTrailer(id: Long): Flow<Resource<Game>> {
        return object : NetworkBoundResource<Game, GameTrailerResponseDto>() {
            override fun loadFromDB(): Flow<Game> {
                return localDataSource.getGameDetail(id).map {
                    Game(it)
                }
            }

            override fun shouldFetch(data: Game?): Boolean {
                return data?.trailerUrl.isNullOrEmpty()
            }

            override suspend fun createCall(): ApiResponse<GameTrailerResponseDto> {
                return remoteDataSource.getGameTrailers(id)
            }

            override suspend fun saveCallResult(data: GameTrailerResponseDto) {
                localDataSource.updateGameTrailer(id, data.results?.firstOrNull()?.data?.max.orEmpty())
            }
        }.asFlow()
    }

    override fun searchGame(query: String): Flow<Resource<List<Game>>> {
        return object : NetworkBoundResource<List<Game>, GamesResponseDto>() {

            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.searchGames(query)
                    .map {
                        it.map { gameEntity ->
                            Game(gameEntity)
                        }
                    }
            }

            override fun shouldFetch(data: List<Game>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): ApiResponse<GamesResponseDto> {
                return remoteDataSource.getAllGames(
                    search = query,
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
    }

    override suspend fun setIsBookmarked(gameId: Long, isBookmarked: Boolean) {
        localDataSource.setIsBookmarked(
            gameId = gameId,
            isBookmarked = isBookmarked,
        )
    }

    override fun getAllBookmarkedGames(): Flow<List<Game>> {
        return localDataSource.getAllFavoriteGames().map { games ->
            games.map {
                Game(it)
            }
        }
    }

    companion object {
        private const val FIRST_PAGE = 1
        private const val PAGE_SIZE = 10
        private const val ORDERING_RATING = "-rating"
    }
}
