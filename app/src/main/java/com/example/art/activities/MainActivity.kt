package com.example.art.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.art.R
import com.example.art.api.ApiClient
import com.example.art.model.User
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var user = User("","")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var usernameInput = findViewById<TextInputLayout>(R.id.username)
        var passInput = findViewById<TextInputLayout>(R.id.pass)
        var btnJoin = findViewById<Button>(R.id.btn_join)

        btnJoin.setOnClickListener {
            var username = usernameInput.editText?.text.toString()
            var pass = passInput.editText?.text.toString()

            if (!validateUserInput(username, pass))
            {
                Toast.makeText(this,"Username or password are empty!", Toast.LENGTH_LONG).show()
            } else
            {
                getKeypass(username, pass)
            }
        }
    }

    fun validateUserInput(username: String, pass: String): Boolean {
        return username != "" && pass != ""
    }

    fun getKeypass(username: String, pass: String) {
        this.user.username = username
        this.user.password = pass

        val callresponse = ApiClient.apiService.getKeypass(this.user)


        callresponse.enqueue(object : Callback<JsonObject> {
            override fun onResponse(p0: Call<JsonObject>, p1: Response<JsonObject>) {
                if (p1.isSuccessful) {
                    val json = p1.body()
                    Log.d("RES", json?.get("keypass")?.asString.toString())
                    // Start a new activity
                    val intent = Intent(this@MainActivity, HomeScreen::class.java)
                    intent.putExtra("keypass", json?.get("keypass")?.asString)
                    startActivity(intent)
                } else {
                    Log.d("RES", p1.body().toString())
                }
            }
            override fun onFailure(p0: Call<JsonObject>, p1: Throwable) {
                Log.d("RES", p1.toString())
            }
        })
    }


}