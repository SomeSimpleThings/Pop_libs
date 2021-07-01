package com.somethingsimple.poplibs.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.somethingsimple.poplibs.data.db.repo.RepoDao
import com.somethingsimple.poplibs.data.db.user.UserDao
import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import com.somethingsimple.poplibs.data.user.model.GithubUser

@Database(entities = [GithubUser::class, GithubRepo::class], version = 1)
abstract class GithubCacheDb : RoomDatabase() {
    abstract fun gitHubUserDao(): UserDao
    abstract fun gitHubRepoDao(): RepoDao
}