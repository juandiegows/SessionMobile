package com.example.sessionmobile.helper

import android.util.Patterns
import com.example.sessionmobile.network.textJD
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputEditText.Requerido(TextLayout: TextInputLayout): TextInputEditText {
    this.setOnFocusChangeListener { v, hasFocus ->
        if (hasFocus) {
            TextLayout.error = ""
        } else {
            if (this.textJD.isEmpty())
                TextLayout.error = "Este campo es obligatorio"
        }

    }
    return this
}

fun TextInputEditText.RequeridoEmail(TextLayout: TextInputLayout): TextInputEditText {
    this.setOnFocusChangeListener { v, hasFocus ->
        if (hasFocus)
            TextLayout.error = ""
        else if (this.textJD.isEmpty())
                TextLayout.error = "Este campo es obligatorio"
            else if (Patterns.EMAIL_ADDRESS.matcher(this.textJD).matches())
                TextLayout.error = ""
            else
                TextLayout.error = "Este campo de ser un correo"


    }
    return this
}