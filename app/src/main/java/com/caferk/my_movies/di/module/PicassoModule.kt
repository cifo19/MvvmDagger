package com.caferk.my_movies.di.module

import com.caferk.my_movies.BaseApplication
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by cafer on 14.2.2018.
 */
@Module(includes = [(NetworkModule::class)])
open class PicassoModule {

    @Provides
    @Singleton
    fun OkHttpDownloader(okHttpClient: OkHttpClient): OkHttpDownloader {
        return OkHttpDownloader(okHttpClient)
    }

    @Provides
    @Singleton
    fun getPicasso(context: BaseApplication, okHttpDownloader: OkHttpDownloader): Picasso {
        return Picasso.Builder(context)
                .downloader(okHttpDownloader)
                .build()
    }
}