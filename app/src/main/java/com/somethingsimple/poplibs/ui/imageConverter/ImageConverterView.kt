package com.somethingsimple.poplibs.ui.imageConverter

import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ImageConverterView : MvpView {

    fun selectImage()
    fun convert()
    fun showPermissionDenied()
    fun showSelectedImage(uri: Uri)
}