package com.example.foodordering.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.foodordering.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up UI elements and listeners
        setVariables()
    }

    private fun setVariables() {
        // Set a click listener for the login button using lambda expression
        binding.loginbtn.setOnClickListener {
            // Retrieve username and password from EditText fields
            val email = binding.username.text.toString()
            val password = binding.password.text.toString()

            // Check if username or password is empty
            if (email.isEmpty() || password.isEmpty()) {
                // Display a toast if username or password is empty
                showToast("Please fill in both username and password")
            } else {
                // Perform Firebase authentication
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Start MainActivity if authentication is successful
                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            // Display a toast with the exception message if authentication fails
                            val errorMessage = task.exception?.message ?: "Authentication failed"
                            showToast(errorMessage)
                        }
                    }

            }

        }

        binding.backk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@LoginActivity, IntroActivity::class.java)
                startActivity(intent)
            }
        })
    }
    // Helper function to show a toast message
    fun showToast(message: String) {
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
    }

}
