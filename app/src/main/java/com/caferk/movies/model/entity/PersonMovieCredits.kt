package com.caferk.movies.model.entity

import com.caferk.movies.model.entity.CastItem
import com.google.gson.annotations.SerializedName

data class PersonMovieCredits(@SerializedName("cast")
                              val cast: List<CastItem>?,
                              @SerializedName("id")
                              val id: Int = 0)