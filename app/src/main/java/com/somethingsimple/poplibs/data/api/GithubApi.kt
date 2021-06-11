package com.somethingsimple.poplibs.data.api

import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import com.somethingsimple.poplibs.data.user.model.GithubUser
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val GITHUB_API_URL = "https://api.github.com"


interface GithubApi {

    @GET("/users")
    fun getUsers(
        @Query("since") since: Int? = null,
        @Query("per_page") perPage: Int? = 10
    ): Single<List<GithubUser>>

    @GET("/users/{username}")
    fun getUserByUsername(@Path("username") username: String): Single<GithubUser>

    @GET("/users/{username}/repos")
    fun getReposForUser(@Path("username") username: String): Single<List<GithubRepo>>

    @GET("/repos/{username}/{reponame}")
    fun getRepoByName(
        @Path("username") username: String,
        @Path("reponame") reponame: String
    ): Single<GithubRepo>

    @GET("/repositories/{id}")
    fun getRepoById(
        @Path("id") id: Int,
    ): Single<GithubRepo>


    companion object Factory {
        fun create(): GithubApi {
            return Retrofit.Builder()
                .client(httpClient())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GITHUB_API_URL)
                .build()
                .create(GithubApi::class.java)
        }

        private fun httpClient() = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor())
            .build()

        private fun loggingInterceptor() = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }
}