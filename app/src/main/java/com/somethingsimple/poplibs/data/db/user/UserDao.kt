package com.somethingsimple.poplibs.data.db.user

import androidx.room.*
import com.somethingsimple.poplibs.data.user.model.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM GithubUser")
    fun fetchUsers(): Single<List<GithubUser>>

    @Query("SELECT * FROM GithubUser WHERE id =:id")
    fun fetchUserById(id: Int): Single<GithubUser>

    @Query("SELECT * FROM GithubUser WHERE login LIKE :login LIMIT 1")
    fun fetchUserByLogin(login: String): Single<GithubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retain(users: List<GithubUser>): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun retain(user: GithubUser): Completable
}