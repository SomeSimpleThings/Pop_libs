package com.somethingsimple.poplibs.exceprion


class SomethingLoadingException(something: String) : Throwable("Cannot load $something") {
}