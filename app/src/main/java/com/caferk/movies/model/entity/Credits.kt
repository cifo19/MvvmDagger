package com.caferk.kotlinbasearchitecture.domain.entity

import com.google.gson.annotations.SerializedName

data class Credits(@SerializedName("cast")
                   val cast: List<CastItem>?,
                   @SerializedName("id")
                   val id: Int = 0,
                   @SerializedName("crew")
                   val crew: List<CrewItem>?)