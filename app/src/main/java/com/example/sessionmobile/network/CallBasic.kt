package com.example.sessionmobile.network

interface CallBasic {

    fun Finish(responseText: String, status: Int);
    fun Error(responseText: String, status: Int);

}