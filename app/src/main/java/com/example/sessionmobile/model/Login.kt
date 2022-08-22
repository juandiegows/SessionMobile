package com.example.sessionmobile.model

class Login {

   lateinit var User:String
   lateinit var Password:String

    constructor(User: String, Password: String) {
        this.User = User
        this.Password = Password
    }
}