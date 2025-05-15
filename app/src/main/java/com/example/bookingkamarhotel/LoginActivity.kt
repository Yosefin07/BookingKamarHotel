package com.example.bookingkamarhotel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LoginActivity : AppCompatActivity() {

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailField = findViewById(R.id.editTextEmail)
        passwordField = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)

        loginButton.setOnClickListener {

            // Mengambil input email dan password
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            // Validasi format email
            if (email.isEmpty()) {
                Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Format email tidak valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Cek apakah password kosong
            if (password.isEmpty()) {
                Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Ambil data user dari SharedPreferences
            val sharedPreferences: SharedPreferences =
                getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val userMapJson = sharedPreferences.getString("user_map", null)

            // Parsing data user yang sudah terdaftar
            val userMap = if (userMapJson != null) {
                Gson().fromJson(userMapJson, object : TypeToken<MutableMap<String, String>>() {}.type)
            } else {
                mutableMapOf<String, String>()
            }

            // Cek apakah email sudah terdaftar dan password cocok
            if (!userMap.containsKey(email)) {
                Toast.makeText(this, "Email tidak terdaftar", Toast.LENGTH_SHORT).show()
            } else if (userMap[email] == password) {
                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USER_EMAIL", email)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
