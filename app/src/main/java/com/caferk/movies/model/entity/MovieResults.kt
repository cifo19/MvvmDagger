package com.caferk.movies.model.entity

import com.caferk.movies.model.entity.ResultsItem
import com.google.gson.annotations.SerializedName

data class MovieResults(@SerializedName("page") val page: Int = 0,
                        @SerializedName("total_pages") val totalPages: Int = 0,
                        @SerializedName("results") val results: List<ResultsItem>?,
                        @SerializedName("total_results") val totalResults: Int = 0)