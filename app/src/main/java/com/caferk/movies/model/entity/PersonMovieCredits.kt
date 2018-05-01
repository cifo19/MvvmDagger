package com.caferk.kotlinbasearchitecture.domain.entity

import com.google.gson.annotations.SerializedName

data class PersonMovieCredits(@SerializedName("cast")
                              val cast: List<CastItem>?,
                              @SerializedName("id")
                              val id: Int = 0)