package com.example.sessionmobile.network

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class ConnectionAPI {


    companion object {
        var URLJD = "http:10.0.2.2:3115";

        enum class method {
            GET,
            POST,
            PUT,
            DELETE
        }

        fun StartQuery(address: String, method: method, data: String, callBasic: CallBasic) {
            CoroutineScope(Dispatchers.IO).launch {
                var client = URL("$URLJD/$address").openConnection() as HttpURLConnection

                client.requestMethod = method.name
                if (method == Companion.method.POST || method == Companion.method.PUT) {
                    client.setRequestProperty("content-type", "application/json")
                    client.outputStream.write(data.encodeToByteArray())
                }

                if (client.errorStream != null) {
                    client.errorStream.bufferedReader().use {
                        callBasic.Error(it.readText(), client.responseCode)
                    }
                    return@launch
                }
                if (client.inputStream != null) {
                    client.inputStream.bufferedReader().use {
                        callBasic.Finish(it.readText(), client.responseCode)
                    }
                    return@launch
                }
            }
        }

    }
}