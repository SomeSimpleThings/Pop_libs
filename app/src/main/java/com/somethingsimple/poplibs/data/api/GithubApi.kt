package com.somethingsimple.poplibs.data.api

import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import com.somethingsimple.poplibs.data.user.model.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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
}