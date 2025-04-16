package com.example.foodordering.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.example.foodordering.R
import com.example.foodordering.databinding.ActivitySignupBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : BaseActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setVariables()
    }

    private fun setVariables() {
        binding.signupbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val email = binding.email.text.toString()
                val password = binding.password2.text.toString()


                if (password.length < 6) {
                    Toast.makeText(this@SignupActivity, "Your password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                    return
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this@SignupActivity, OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            // Registration success
                            Toast.makeText(this@SignupActivity, "Registration successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@SignupActivity, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            // Registration failed
                            Toast.makeText(this@SignupActivity, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }
        )
        binding.login2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                startActivity(intent)
    }
})
        binding.back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@SignupActivity, IntroActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
