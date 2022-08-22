package com.example.sessionmobile.helper

import android.content.Context
import androidx.appcompat.app.AlertDialog

class AlertJD {

    companion object {
        fun AlertOK(context: Context, mensaje:String){
            AlertDialog.Builder(context)
                .setMessage(mensaje)
                .setPositiveButton("Ok") { _, _ -> }
                .create()
                .show()
        }
    }
}