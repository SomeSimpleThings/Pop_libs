package com.somethingsimple.poplibs.data.repo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.somethingsimple.poplibs.data.user.model.GithubUser

@Entity(
    foreignKeys = [ForeignKey(
        entity = GithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class GithubRepo(
    @ColumnInfo @SerializedName("description") val description: String,
    @PrimaryKey @SerializedName("id") val id: Int,
    @ColumnInfo @SerializedName("language") val language: String,
    @ColumnInfo @SerializedName("name") val name: String,
    @ColumnInfo @SerializedName("open_issues") val openIssues: Int,
    @ColumnInfo val userId: Int,
    @ColumnInfo @SerializedName("watchers_count") val watchersCount: Int
)