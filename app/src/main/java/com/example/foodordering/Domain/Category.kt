package com.example.foodordering.Domain

class Category {
    var Id: Int = 0
        get() = field
        set(value) {
            field = value
        }

    var ImagePath: String? = null
        get() = field
        set(value) {
            field = value
        }

    var Name: String? = null
        get() = field
        set(value) {
            field = value
        }

    // Méthode toString
    override fun toString(): String {
        return "id=$Id, imagePath=$ImagePath, name=$Name"
    }
}

