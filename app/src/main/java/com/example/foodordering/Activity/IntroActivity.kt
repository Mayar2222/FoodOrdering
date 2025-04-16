package com.example.foodordering.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.foodordering.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setVariables()
    }

    private fun setVariables() {


        binding.signup.setOnClickListener {
            Log.d("IntroActivity", "Signup Button Clicked")
            val intent = Intent(this@IntroActivity, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.login.setOnClickListener {
            Log.d("IntroActivity", "login Button Clicked")
            val intent = Intent(this@IntroActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
