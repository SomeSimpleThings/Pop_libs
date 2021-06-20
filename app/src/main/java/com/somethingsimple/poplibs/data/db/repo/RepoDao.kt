package com.somethingsimple.poplibs.data.db.repo

import androidx.room.*
import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface RepoDao {

    @Query("SELECT * FROM GithubRepo WHERE userId = :userId")
    fun fetchReposForUser(userId: Int): Single<List<GithubRepo>>

    @Query("SELECT * FROM GithubRepo WHERE id = :id ")
    fun fetchRepoById(id: Int): Single<GithubRepo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retain(repos: List<GithubRepo>): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun retain(repos: GithubRepo): Completable
}