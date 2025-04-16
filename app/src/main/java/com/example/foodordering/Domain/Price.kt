package com.example.foodordering.Domain

class Price {
    var Id: Int = 0
        get() = field
        set(Value) { field = Value }

    var Value: String? = null
        get() = field
        set(Value) { field = Value }

    override fun toString(): String {
        return " $Value"
    }
}