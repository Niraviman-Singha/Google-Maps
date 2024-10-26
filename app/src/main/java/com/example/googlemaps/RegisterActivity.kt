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

class RegisterActivity : AppCompatActivity() {

    private lateinit var authViewModel:AuthenticationViewModel
    private lateinit var fireStoreViewModel: FireStoreViewModel
    private lateinit var editTextName:EditText
    private lateinit var editTextEmail:EditText
    private lateinit var editTextPassword:EditText
    private lateinit var editTextConPassword:EditText
    private lateinit var buttonRegister:Button
    private lateinit var textViewLogin:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextName = findViewById(R.id.nameET)
        editTextEmail = findViewById(R.id.emailET)
        editTextPassword = findViewById(R.id.passwordET)
        editTextConPassword = findViewById(R.id.confirmPasswordET)
        buttonRegister = findViewById(R.id.registerBtn)
        textViewLogin = findViewById(R.id.loginTV)

        authViewModel = ViewModelProvider(this).get(AuthenticationViewModel::class.java)
        fireStoreViewModel = ViewModelProvider(this).get(FireStoreViewModel::class.java)

        buttonRegister.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            authViewModel.register(email,password, {
                val displayName = editTextName.text.toString()
                val location = "location not available!"

                fireStoreViewModel.saveUser(authViewModel.getCurrentUserId(), displayName, email, location)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },{errorMessage->
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            })
        }

        textViewLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        if(Firebase.auth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}