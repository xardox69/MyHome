package com.myhome.app.domain

import java.util.HashMap

class Params private constructor() {

    private val parameters = HashMap<String, Any>()

    fun putInt(key: String,
               value: Int) {
        parameters[key] = value
    }

    fun getParameters(): Map<String, Any> {
        return parameters
    }

    fun getInt(key: String?,
               defaultValue: Int): Int {
        val `object` = parameters[key] ?: return defaultValue
        return try {
            `object` as Int
        } catch (e: ClassCastException) {
            defaultValue
        }

    }

    fun putString(key: String,
                  value: String) {
        parameters[key] = value
    }


    fun putList(key: String,
                value: List<String>) {
        parameters[key] = value
    }


    fun getString(key: String,
                  defaultValue: String?): String? {
        val `object` = parameters[key] ?: return defaultValue
        return try {
            `object` as String
        } catch (e: ClassCastException) {
            defaultValue
        }

    }

    fun putLong(key: String,
                value: Long) {
        parameters[key] = value
    }

    fun putFloat(key: String,
                 value: Float) {
        parameters[key] = value
    }

    fun putData(key: String,
                value: Any) {
        parameters[key] = value
    }

    fun getFloat(key: String,
                 defaultValue: Long): Float {
        val `object` = parameters[key] ?: return defaultValue.toFloat()
        return try {
            `object` as Float
        } catch (e: ClassCastException) {
            e.printStackTrace()
            defaultValue.toFloat()
        }

    }

    fun getData(key: String,
                defaultValue: Any?): Any? {
        val `object` = parameters[key] ?: return defaultValue
        return try {
            `object`
        } catch (e: ClassCastException) {
            e.printStackTrace()
            defaultValue
        }

    }

    fun getDouble(key: String,
                  defaultValue: Long): Double {
        val `object` = parameters[key] ?: return defaultValue.toDouble()
        return try {
            `object` as Double
        } catch (e: ClassCastException) {
            e.printStackTrace()
            defaultValue.toDouble()
        }

    }

    fun getLong(key: String,
                defaultValue: Long): Long {
        val `object` = parameters[key] ?: return defaultValue
        return try {
            `object` as Long
        } catch (e: ClassCastException) {
            e.printStackTrace()
            defaultValue
        }

    }

    companion object {
        val EMPTY = create()

        fun create(): Params {
            return Params()
        }
    }
}