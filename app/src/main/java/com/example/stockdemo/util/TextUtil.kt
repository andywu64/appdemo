package com.example.stockdemo.util

fun textValueDefault(value: String) : String {
    if (value.isNotEmpty()) {
        return value
    }
    return "-"
}