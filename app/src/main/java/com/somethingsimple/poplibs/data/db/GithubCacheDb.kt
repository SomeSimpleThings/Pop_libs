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
    abstract fun gitHubReDao(): RepoDao

    companion object {
        private const val DB_NAME = "gb_cache.db"
        private var instance: GithubCacheDb? = null
        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, GithubCacheDb::class.java, DB_NAME)
                    .build()
            }
        }
    }
}