package com.caferk.movies.model.entity

import com.google.gson.annotations.SerializedName

data class CrewItem(@SerializedName("gender")
                    val gender: Int = 0,
                    @SerializedName("credit_id")
                    val creditId: String = "",
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("profile_path")
                    val profilePath: String = "",
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("department")
                    val department: String = "",
                    @SerializedName("job")
                    val job: String = "")