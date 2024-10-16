package com.mlefrapper.androidstarterkit.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameItemDto(
    @Json(name = "id")
    val id: Long? = null,
    @Json(name = "slug")
    val slug: String? = null,
    @Json(name = "description_raw")
    val description: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "released")
    val released: String? = null,
    @Json(name = "tba")
    val tba: Boolean? = null,
    @Json(name = "background_image")
    val backgroundImage: String? = null,
    @Json(name = "rating")
    val rating: Double? = null,
    @Json(name = "rating_top")
    val ratingTop: Int? = null,
    @Json(name = "ratings")
    val ratings: List<RatingDto>? = null,
    @Json(name = "ratings_count")
    val ratingsCount: Int? = null,
    @Json(name = "reviews_text_count")
    val reviewsTextCount: Int? = null,
    @Json(name = "added")
    val added: Int? = null,
    @Json(name = "added_by_status")
    val addedByStatus: AddedByStatusDto? = null,
    @Json(name = "metacritic")
    val metacritic: Int? = null,
    @Json(name = "playtime")
    val playtime: Int? = null,
    @Json(name = "suggestions_count")
    val suggestionsCount: Int? = null,
    @Json(name = "updated")
    val updated: String? = null,
    @Json(name = "user_game")
    val userGame: Any? = null,
    @Json(name = "reviews_count")
    val reviewsCount: Int? = null,
    @Json(name = "saturated_color")
    val saturatedColor: String? = null,
    @Json(name = "dominant_color")
    val dominantColor: String? = null,
    @Json(name = "platforms")
    val platforms: List<PlatformDto>? = null,
    @Json(name = "parent_platforms")
    val parentPlatforms: List<ParentPlatformDto>? = null,
    @Json(name = "genres")
    val genres: List<GenreDto>? = null,
    @Json(name = "stores")
    val stores: List<StoreDto>? = null,
    @Json(name = "clip")
    val clip: Any? = null,
    @Json(name = "tags")
    val tags: List<TagDto>? = null,
    @Json(name = "esrb_rating")
    val esrbRating: EsrbRatingDto? = null,
    @Json(name = "short_screenshots")
    val shortScreenshots: List<ShortScreenshotDto>? = null,
) {
    @JsonClass(generateAdapter = true)
    data class RatingDto(
        @Json(name = "id")
        val id: Int? = null,
        @Json(name = "title")
        val title: String? = null,
        @Json(name = "count")
        val count: Int? = null,
        @Json(name = "percent")
        val percent: Double? = null,
    )

    @JsonClass(generateAdapter = true)
    data class AddedByStatusDto(
        @Json(name = "yet")
        val yet: Int? = null,
        @Json(name = "owned")
        val owned: Int? = null,
        @Json(name = "beaten")
        val beaten: Int? = null,
        @Json(name = "toplay")
        val toplay: Int? = null,
        @Json(name = "dropped")
        val dropped: Int? = null,
        @Json(name = "playing")
        val playing: Int? = null,
    )

    @JsonClass(generateAdapter = true)
    data class PlatformDto(
        @Json(name = "platform")
        val platform: PlatformChildDto? = null,
        @Json(name = "released_at")
        val releasedAt: String? = null,
        @Json(name = "requirements_en")
        val requirementsEn: RequirementsEnDto? = null,
        @Json(name = "requirements_ru")
        val requirementsRu: RequirementsRuDto? = null,
    ) {
        @JsonClass(generateAdapter = true)
        data class PlatformChildDto(
            @Json(name = "id")
            val id: Int? = null,
            @Json(name = "name")
            val name: String? = null,
            @Json(name = "slug")
            val slug: String? = null,
            @Json(name = "image")
            val image: Any? = null,
            @Json(name = "year_end")
            val yearEnd: Any? = null,
            @Json(name = "year_start")
            val yearStart: Int? = null,
            @Json(name = "games_count")
            val gamesCount: Int? = null,
            @Json(name = "image_background")
            val imageBackground: String? = null,
        )

        @JsonClass(generateAdapter = true)
        data class RequirementsEnDto(
            @Json(name = "minimum")
            val minimum: String? = null,
            @Json(name = "recommended")
            val recommended: String? = null,
        )

        @JsonClass(generateAdapter = true)
        data class RequirementsRuDto(
            @Json(name = "minimum")
            val minimum: String? = null,
            @Json(name = "recommended")
            val recommended: String? = null,
        )
    }

    @JsonClass(generateAdapter = true)
    data class ParentPlatformDto(
        @Json(name = "platform")
        val platform: PlatformDto? = null,
    ) {
        @JsonClass(generateAdapter = true)
        data class PlatformDto(
            @Json(name = "id")
            val id: Int? = null,
            @Json(name = "name")
            val name: String? = null,
            @Json(name = "slug")
            val slug: String? = null,
        )
    }

    @JsonClass(generateAdapter = true)
    data class GenreDto(
        @Json(name = "id")
        val id: Int? = null,
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "slug")
        val slug: String? = null,
        @Json(name = "games_count")
        val gamesCount: Int? = null,
        @Json(name = "image_background")
        val imageBackground: String? = null,
    )

    @JsonClass(generateAdapter = true)
    data class StoreDto(
        @Json(name = "id")
        val id: Int? = null,
        @Json(name = "store")
        val store: StoreChildDto? = null,
    ) {
        @JsonClass(generateAdapter = true)
        data class StoreChildDto(
            @Json(name = "id")
            val id: Int? = null,
            @Json(name = "name")
            val name: String? = null,
            @Json(name = "slug")
            val slug: String? = null,
            @Json(name = "domain")
            val domain: String? = null,
            @Json(name = "games_count")
            val gamesCount: Int? = null,
            @Json(name = "image_background")
            val imageBackground: String? = null,
        )
    }

    @JsonClass(generateAdapter = true)
    data class TagDto(
        @Json(name = "id")
        val id: Int? = null,
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "slug")
        val slug: String? = null,
        @Json(name = "language")
        val language: String? = null,
        @Json(name = "games_count")
        val gamesCount: Int? = null,
        @Json(name = "image_background")
        val imageBackground: String? = null,
    )

    @JsonClass(generateAdapter = true)
    data class EsrbRatingDto(
        @Json(name = "id")
        val id: Int? = null,
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "slug")
        val slug: String? = null,
    )

    @JsonClass(generateAdapter = true)
    data class ShortScreenshotDto(
        @Json(name = "id")
        val id: Int? = null,
        @Json(name = "image")
        val image: String? = null,
    )
}
