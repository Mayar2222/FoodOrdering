package com.example.foodordering.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.foodordering.Domain.Foods
import com.example.foodordering.R
import com.example.foodordering.databinding.ActivityDetailsBinding

class DetailsActivity2 : BaseActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var foodObject: Foods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentExtra()
        setVariables()
    }

    private fun getIntentExtra() {
        // Récupérez les données de l'intent
        foodObject = intent.getSerializableExtra("FOOD_OBJECT_KEY") as? Foods
            ?: throw IllegalArgumentException("Food object not found in Intent")
    }

    private fun setVariables() = binding.run {
        backkbtn.setOnClickListener { finish() }

        // Utilisez foodObject directement sans vérification supplémentaire car getIntentExtra garantit qu'il n'est pas null
        Glide.with(this@DetailsActivity2)
            .load(foodObject.ImagePath)
            .into(imageView)

        priceee.text = "$${foodObject.Price}"
        titleee.text = foodObject.Title
        textdetails.text = foodObject.Description
        rateTxt.text = "${foodObject.Star} Rating"

        ratingBar.rating = foodObject.Star.toFloat()
        totalprice.text = "${foodObject.NumberInCart * foodObject.Price}"
    }

}
