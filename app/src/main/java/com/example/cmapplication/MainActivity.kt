package com.example.cmapplication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cmapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        emailFocusListener()
        passwordFocusListener()

        binding.loginButton.setOnClickListener(View.OnClickListener {
            if (binding.emailEditText.text.toString() == "aa@bb.cc" && binding.passwordEditText.text.toString() == "12345678"){
                val Intent = Intent(this,Home::class.java)
                startActivity(Intent)
                Toast.makeText(this, "Login Successful" , Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Email or password incorrect" , Toast.LENGTH_SHORT).show()
            }
        })

    }
    private fun emailFocusListener()
    {
        binding.emailEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.emailContainer.helperText = validEmail()
            }

        }
    }
    private fun validEmail(): String?
    {
        val emailText = binding.emailEditText.text.toString()
        if(emailText.matches("^[a-z]+@[a-z]+.[a-z]{2,4}$".toRegex()))
        {
        return "รูปแบบอีเมลถูกต้อง"
        }
        else {
            return "รูปแบบอีเมลไม่ถูกต้อง"
        }
    }

    private fun passwordFocusListener()
    {
        binding.passwordEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String?
    {
        val passwordText = binding.passwordEditText.text.toString()
        if(passwordText.length >= 8)
        {
            return "Minimum 8 Character Password"
        }
        return null
    }
}