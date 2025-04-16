package com.example.foodordering.Domain

class Location {
    var Id: Int = 0
        get() = field
        set(value) { field = value }

    var loc: String? = "null"
        get() = field
        set(value) { field = value }

    override fun toString(): String {
        return "$loc"
    }
}