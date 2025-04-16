package com.example.foodordering.Activity

import BestFoodsAdapter
import CategoryAdapter
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.foodordering.Domain.Category
import com.example.foodordering.Domain.Foods
import com.example.foodordering.Domain.Price
import com.example.foodordering.R
import com.example.foodordering.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.sql.Time

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLocation()
        initTime()
        initPrice()
        initBestFood()
        initCategory()
        setVariables()


    }

    private fun setVariables() {
        binding.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Si vous souhaitez également fermer l'activité actuelle après le démarrage de LoginActivity
        }
        binding.SearchEdit.setOnClickListener {
            val text = binding.SearchEdit.text.toString()
            if (text.isNotEmpty()) {
                val intent = Intent(this@MainActivity, ListFoodActivity::class.java)
                intent.putExtra("text", text)
                intent.putExtra("isSearch", true)
                startActivity(intent)
            }
        }

    }

    private fun initBestFood() {
        val myRef = database.getReference("Foods")
        binding.progressBar.visibility = View.VISIBLE

        val list = ArrayList<Foods>()

        val query = myRef.orderByChild("BestFood").equalTo(false)
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(Foods::class.java)!!)
                    }
                    if (list.size > 0) {

                        binding.BestFoodView.layoutManager =
                            LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, true)
                        binding.BestFoodView.adapter = BestFoodsAdapter(list)
                    }
                }
                binding.progressBar.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                // Gérer l'annulation de l'événement
            }
        })
    }





    private fun initLocation() {
        val myRef = database.getReference("Location")
        val locationList: ArrayList<com.example.foodordering.Domain.Location> = ArrayList()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        val location = issue.getValue(com.example.foodordering.Domain.Location::class.java)
                        location?.let {
                            locationList.add(it)
                        }
                    }

                    val adapter = ArrayAdapter<com.example.foodordering.Domain.Location>(this@MainActivity, android.R.layout.simple_spinner_item, locationList)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinner.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Gérer l'annulation de l'événement

            }
        })
    }

    private fun initTime() {
        val myRef = database.getReference("Time")
        val timeList: ArrayList<com.example.foodordering.Domain.Time> = ArrayList()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        val time = issue.getValue(com.example.foodordering.Domain.Time::class.java)
                        time?.let {
                            timeList.add(it)
                        }
                    }

                    val adapter = ArrayAdapter<com.example.foodordering.Domain.Time>(this@MainActivity, android.R.layout.simple_spinner_item, timeList)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinner1.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Gérer l'annulation de l'événement

            }
        })
    }

    private fun initPrice() {
        val myRef = database.getReference("Price")
        val PriceList: ArrayList<Price> = ArrayList()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        val price = issue.getValue(Price::class.java)
                        price?.let {
                            PriceList.add(it)
                        }
                    }

                    val adapter = ArrayAdapter<Price>(this@MainActivity, android.R.layout.simple_spinner_item, PriceList)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinner2.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Gérer l'annulation de l'événement

            }
        })
    }

    private fun initCategory() {
        val myRef = database.getReference("Category")
        binding.progressBar2.visibility = View.VISIBLE

        val list = ArrayList<Category>()

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        list.add(issue.getValue(Category::class.java)!!)
                    }
                    if (list.size > 0) {
                        binding.category.layoutManager =
                            GridLayoutManager(this@MainActivity, 4)
                        binding.category.adapter = CategoryAdapter(list)
                    }
                }
                binding.progressBar2.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                // Gérer l'annulation de l'événement
                binding.progressBar2.visibility = View.GONE
            }
        })
    }





}
