package com.example.sessionmobile.network

import com.google.android.material.textfield.TextInputEditText
import org.json.JSONArray
import org.json.JSONObject
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

@OptIn(ExperimentalStdlibApi::class)
fun JSONObject.toClass(nameClass: String): Any {
    var oClass = Class.forName(nameClass)
    var any = oClass.newInstance()

    oClass.declaredFields.forEach {
        it.isAccessible = true
        it.set(
            any, when (it.genericType) {
                typeOf<Int>().javaType -> this.getInt(it.name)
                typeOf<String>().javaType -> this.getString(it.name)
                typeOf<Double>().javaType -> this.getDouble(it.name)
                typeOf<Long>().javaType -> this.getLong(it.name)
                typeOf<Boolean>().javaType -> this.getBoolean(it.name)
                else -> {
                    try {
                        this.toClass(it.type.name)
                    } catch (e: Exception) {
                        try {
                            this.getJSONArray(it.name).toClassList(it.type.name)
                        } catch (er: Exception) {

                        }

                    }

                }
            }
        )
    }

    return any
}


fun JSONArray.toClassList(nameClass: String): List<Any> {
    var lista = ArrayList<Any>()
    for (i in 0 until this.length()) {
        lista.add(this.getJSONObject(i).toClass(nameClass))
    }
    return lista
}

fun Any.toJson(): String {
    var objetc = JSONObject();
    this.javaClass.declaredFields.forEach {
        it.isAccessible = true
        objetc.put(it.name, it.get(this))
    }
    return objetc.toString()
}

fun List<Any>.toJsonList(): String {
    var list = JSONArray()
    this.forEach {
        list.put(JSONObject(it.toJson()))
    }
    return list.toString()
}

inline fun <reified T : Any> Any.Cast(): T {
    return this as T
}

var TextInputEditText.textJD: String
    get() = this.text.toString()
    set(value) {this.setText(value)}

