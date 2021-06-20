package com.somethingsimple.poplibs.data.user.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class GithubUser(
    @ColumnInfo @SerializedName("avatar_url") val avatarUrl: String,
    @ColumnInfo @SerializedName("company") val company: String?,
    @ColumnInfo @SerializedName("email") val email: String?,
    @ColumnInfo @SerializedName("followers") val followers: Int,
    @ColumnInfo @SerializedName("following") val following: Int,
    @PrimaryKey @SerializedName("id") val id: Int,
    @ColumnInfo @SerializedName("location") val location: String?,
    @ColumnInfo @SerializedName("login") val login: String,
    @ColumnInfo @SerializedName("name") val name: String?,
    @ColumnInfo @SerializedName("public_repos") val publicRepos: Int,
) : Parcelable {
}