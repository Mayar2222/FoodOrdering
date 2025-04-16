package com.example.foodordering.Activity

import FoodListAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodordering.Domain.Foods
import com.example.foodordering.R
import com.example.foodordering.databinding.ActivityListFoodBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class ListFoodActivity : BaseActivity() {
    private lateinit var binding: ActivityListFoodBinding
    private lateinit var adapterListFood: FoodListAdapter

    private var categoryId: Int = 0
    private var categoryName: String? = null
    private var searchText: String? = null
    private var isSearch: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentExtra()
        initList()

    }
    private fun getIntentExtra() {
        categoryId = intent.getIntExtra("categoryId", 0)
        categoryName = intent.getStringExtra("categoryName") // Correction de la clé
        searchText = intent.getStringExtra("Text")
        isSearch = intent.getBooleanExtra("IsSearch", false)
        binding.titletxtt.text = categoryName
        binding.backbtn.setOnClickListener { finish() }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun initList() {
        val myRef = database.getReference("Foods")
        binding.progressBar6.visibility = View.VISIBLE
        val list = ArrayList<Foods>()

            myRef.orderByChild("Title").startAt(searchText).endAt(searchText )

            // Assurez-vous de convertir categoryId en une chaîne si c'est une chaîne dans la base de données
            myRef.orderByChild("CategoryId").equalTo(categoryId.toString())


        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(Foods::class.java)!!)
                    }
                    if (list.size > 0) {
                        binding.FoodListView.layoutManager = GridLayoutManager(this@ListFoodActivity, 2)
                        adapterListFood = FoodListAdapter(list)
                        binding.FoodListView.adapter = adapterListFood
                    }
                }
                binding.progressBar6.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                // Gérer l'annulation de l'événement
            }
        })
    }


}