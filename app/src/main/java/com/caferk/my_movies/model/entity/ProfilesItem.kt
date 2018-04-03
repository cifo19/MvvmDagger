package com.caferk.kotlinbasearchitecture.domain.entity

import com.google.gson.annotations.SerializedName

data class ProfilesItem(@SerializedName("file_path")
                        val filePath: String = "",
                        @SerializedName("aspect_ratio")
                        val aspectRatio: Double = 0.0,
                        @SerializedName("vote_average")
                        val voteAverage: Double = 0.0,
                        @SerializedName("width")
                        val width: Int = 0,
                        @SerializedName("iso_639_1")
                        val iso: String = "",
                        @SerializedName("vote_count")
                        val voteCount: Int = 0,
                        @SerializedName("height")
                        val height: Int = 0)