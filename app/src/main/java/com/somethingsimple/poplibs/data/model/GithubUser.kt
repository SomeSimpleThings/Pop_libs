package com.somethingsimple.poplibs.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    var id: String,
    val login: String
) : Parcelable {
}