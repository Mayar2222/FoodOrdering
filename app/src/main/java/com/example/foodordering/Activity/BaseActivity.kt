package com.example.foodordering.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodordering.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

open class BaseActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

    }
}