package com.mlefrapper.androidstarterkit.domain.model

import com.mlefrapper.androidstarterkit.data.local.entity.GameEntity
import com.mlefrapper.androidstarterkit.utils.ConverterDate
import com.mlefrapper.androidstarterkit.utils.toString
import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: Long,
    val slug: String,
    val name: String,
    val released: String,
    val tba: Boolean,
    val backgroundImage: String,
    val rating: Double,
    val ratingTop: Int,
    val ratingsCount: Int,
    val reviewsTextCount: Int,
    val added: Int,
    val metacritic: Int,
    val playtime: Int,
    val suggestionsCount: Int,
    val updated: String,
    val reviewsCount: Int,
    val saturatedColor: String,
    val dominantColor: String,
    val parentPlatforms: List<String>,
    val genres: List<String>,
    val stores: List<String>,
    val tags: List<String>,
    val esrbRating: String,
    val shortScreenshots: List<String>,
    val isFavorites: Boolean,
    val description: String,
    val trailerUrl: String?,
) {
    constructor(data: GameEntity?) : this(
        id = data?.id ?: 0,
        slug = data?.slug.orEmpty(),
        name = data?.name.orEmpty(),
        released = data?.released?.toString(ConverterDate.SQL_DATE).orEmpty(),
        tba = data?.tba ?: false,
        backgroundImage = data?.backgroundImage.orEmpty(),
        rating = data?.rating ?: 0.0,
        ratingTop = data?.ratingTop ?: 0,
        ratingsCount = data?.ratingsCount ?: 0,
        reviewsTextCount = data?.reviewsTextCount ?: 0,
        added = data?.added ?: 0,
        metacritic = data?.metacritic ?: 0,
        playtime = data?.playtime ?: 0,
        suggestionsCount = data?.suggestionsCount ?: 0,
        updated = data?.updated.orEmpty(),
        reviewsCount = data?.reviewsCount ?: 0,
        saturatedColor = data?.saturatedColor.orEmpty(),
        dominantColor = data?.dominantColor.orEmpty(),
        parentPlatforms = data?.parentPlatforms.orEmpty(),
        genres = data?.genres.orEmpty(),
        stores = data?.stores.orEmpty(),
        tags = data?.tags.orEmpty(),
        esrbRating = data?.esrbRating.orEmpty(),
        shortScreenshots = data?.shortScreenshots.orEmpty(),
        isFavorites = data?.isFavorites ?: false,
        description = data?.description.orEmpty(),
        trailerUrl = data?.trailerUrl,
    )
}
