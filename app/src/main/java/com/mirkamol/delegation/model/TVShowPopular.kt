package com.mirkamol.delegation.model


data class TVShowPopular(
    val total: String? = null,
    val page: Int? = null,
    val pages: Int? = null,
    val tv_shows: ArrayList<TVShow>? = null
)

data class TVShow(
    val id: Long,
    val name: String? = null,
    val start_data: String? = null,
    val end_data: String? = null,
    val network: String? = null,
    val status: String? = null,
    val image_thumbnail_path: String? = null
)
