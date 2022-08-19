package com.example.sessionmobile.network

interface CallBasic {

    fun Finiish(responseText: String, status: Int);
    fun Error(responseText: String, status: Int);

}