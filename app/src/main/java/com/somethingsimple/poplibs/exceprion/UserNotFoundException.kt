package com.somethingsimple.poplibs.exceprion

class UserNotFoundException(id: String) : Throwable("user with id $id not found") {
}