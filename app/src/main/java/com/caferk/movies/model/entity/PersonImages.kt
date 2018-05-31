package com.caferk.movies.model.entity

import com.caferk.movies.model.entity.ProfilesItem
import com.google.gson.annotations.SerializedName

data class PersonImages(@SerializedName("profiles")
                        val profiles: List<ProfilesItem>?,
                        @SerializedName("id")
                        val id: Int = 0)