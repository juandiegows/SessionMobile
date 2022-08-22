package com.example.sessionmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.sessionmobile.databinding.ActivityLoginBinding
import com.example.sessionmobile.helper.AlertJD
import com.example.sessionmobile.model.Funcao
import com.example.sessionmobile.model.Login
import com.example.sessionmobile.network.*
import org.json.JSONArray

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnIngresar.setOnClickListener {
            loguearse()
        }
    }

    private fun loguearse() {
        if (binding.txtEmail.text.isNullOrEmpty() || binding.txtPassword.text.isNullOrEmpty()) {

            if (binding.txtEmail.text.isNullOrEmpty())
                binding.txtSEmail.error = "Este campo es obligatorio"

            if (binding.txtPassword.text.isNullOrEmpty())
                binding.txtSPassword.error = "Este campo es obligatorio"

            AlertJD.AlertOK(this, "Se debe llenar todos los campos")

            return
        }



        ConnectionAPI.StartQuery("api/Auth", ConnectionAPI.Companion.method.POST,
            Login(binding.txtEmail.textJD, binding.txtPassword.textJD).toJson(),
            object : CallBasic {

                override fun Finish(responseText: String, status: Int) {
                    Log.e("Finish", "Finish($status): $responseText")
                    runOnUiThread {
                        if (responseText.equals("true", true)) {

                        }else{
                            AlertJD.AlertOK(this@LoginActivity,"Usuario o contraseña incorrecta")
                        }
                    }
                }

                override fun Error(responseText: String, status: Int) {
                    runOnUiThread {
                        AlertJD.AlertOK(this@LoginActivity, responseText)
                    }
                }
            })
    }
}