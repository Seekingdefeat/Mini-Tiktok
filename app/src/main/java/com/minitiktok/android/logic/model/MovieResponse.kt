package com.minitiktok.android.logic.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("data") val respDate: MovieResponseData,
    @SerializedName("extra") val extraMovieResponseData: ExtraMovieResponseData
)

data class MovieResponseData(
    @SerializedName("active_time") val activeTime: String?,
    val description: String?,
    @SerializedName("error_code") val errorCode: Long?,
    val movies: List<Movie>?
)

data class ExtraMovieResponseData(
    val description: String?,
    @SerializedName("error_code") val errorCode: Long?,
    val logid: String?,
    val now: Long?,
    @SerializedName("sub_description") val subDescription: String?,
    @SerializedName("sub_error_code") val subErrorCode: Long?
)

data class Movie(
    val actors: List<String>?,
    val areas: List<String>?,
    val directors: List<String>?,
    @SerializedName("discussion_hot") val discussionHot: Long?,
    val hot: String?,
    val id: String?,
    @SerializedName("influence_hot") val influenceHot: String?,
    @SerializedName("maoyan_id") val maoyanId: String?,
    val name: String?,
    @SerializedName("name_en") val englishNam: String?,
    val poster: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("search_hot") val searchHot: String?,
    val tags: List<String>?,
    @SerializedName("topic_hot") val topicHot: String?,
    val type: Int?
)