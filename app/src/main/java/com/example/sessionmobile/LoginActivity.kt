package com.example.sessionmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sessionmobile.model.Funcao
import com.example.sessionmobile.network.*
import org.json.JSONArray
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ConnectionAPI().StartQuery("api/Funcaos", ConnectionAPI.method.GET, "", object : CallBasic {

            override fun Finish(responseText: String, status: Int) {
                Log.e("Finish", "Finish($status): $responseText")
                var funcao: List<Funcao> =
                    JSONArray(responseText).toClassList(Funcao().javaClass.name).Cast()

                Log.e("JsonObject", "Finish: ${funcao.toJsonList()}")
                funcao.forEach {
                    Log.e("Data", "${it.id} ${it.funcao}" )
                }
            }

            override fun Error(responseText: String, status: Int) {
                Log.e("Error", "Error ($status): $responseText")
            }
        })
    }
}