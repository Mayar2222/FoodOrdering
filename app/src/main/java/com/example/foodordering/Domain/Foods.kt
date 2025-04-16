package com.example.foodordering.Domain

import java.io.Serializable

data class Foods(
    var CategoryId: Int = 0,
    var Description: String? = null,
    var BestFood: Boolean = true,
    var Id: Int = 0,
    var LocationId: Int = 0,
    var Price: Double = 0.0,
    var ImagePath: String? = null,
    var PriceId: Int = 0,
    var Star: Double = 0.0,
    var TimeId: Int = 0,
    var TimeValue: Int = 0,
    var Title: String? = null,
    var NumberInCart: Int = 0
) : Serializable {

    override fun toString(): String {
        return "$Title"
    }
}
