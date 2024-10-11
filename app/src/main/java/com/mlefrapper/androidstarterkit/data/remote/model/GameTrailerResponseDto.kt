package com.mlefrapper.androidstarterkit.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameTrailerResponseDto(
    @Json(name = "count")
    val count: Int? = null,
    @Json(name = "next")
    val next: Any? = null,
    @Json(name = "previous")
    val previous: Any? = null,
    @Json(name = "results")
    val results: List<ResultDto?>? = null,
) {
    @JsonClass(generateAdapter = true)
    data class ResultDto(
        @Json(name = "id")
        val id: Int? = null,
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "preview")
        val preview: String? = null,
        @Json(name = "data")
        val data: DataDto? = null,
    ) {
        @JsonClass(generateAdapter = true)
        data class DataDto(
            @Json(name = "480")
            val x480: String? = null,
            @Json(name = "max")
            val max: String? = null,
        )
    }
}
