package com.example.googlemaps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthenticationViewModel
    private lateinit var buttonLogin:Button
    private lateinit var editTextEmail:EditText
    private lateinit var editTextPassword:EditText
    private lateinit var textViewRegister:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin = findViewById(R.id.loginBtn)
        editTextEmail = findViewById(R.id.emailET)
        editTextPassword = findViewById(R.id.passwordET)
        textViewRegister = findViewById(R.id.registerTV)

        viewModel = ViewModelProvider(this).get(AuthenticationViewModel::class.java)

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            viewModel.login(email, password, {
                                             startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, {errorMessage->
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            })
        }
        textViewRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}