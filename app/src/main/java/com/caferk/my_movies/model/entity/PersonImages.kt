package com.caferk.kotlinbasearchitecture.domain.entity

import com.google.gson.annotations.SerializedName

data class PersonImages(@SerializedName("profiles")
                        val profiles: List<ProfilesItem>?,
                        @SerializedName("id")
                        val id: Int = 0)