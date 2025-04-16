package com.example.foodordering.Domain

class Time {
    var Id: Int = 0
        get() = field
        set(value) { field = value }

    var Value: String? = "null"
        get() = field
        set(value) { field = value }

    override fun toString(): String {
        return "$Value"
    }
}