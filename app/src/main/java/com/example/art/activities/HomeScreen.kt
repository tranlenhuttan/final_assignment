package com.example.art.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.art.R
import com.example.art.adapters.EntityAdapter
import com.example.art.api.ApiClient
import com.example.art.model.ListOfEntities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreen : AppCompatActivity() {
    // Attributes
    private lateinit var keypass : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.keypass = intent.getStringExtra("keypass")!!

        val resClient = ApiClient.apiService.getData(this.keypass)
        resClient.enqueue(object : Callback<ListOfEntities> {
            override fun onResponse(p0: Call<ListOfEntities>, p1: Response<ListOfEntities>) {
                if (p1.isSuccessful) {
                    Log.d("RES", p1.body()?.entityTotal.toString())
                    val recyclerView = findViewById<RecyclerView>(R.id.list_of_entities)
                    val adapter = EntityAdapter(p1.body()!!)
                    recyclerView.layoutManager = LinearLayoutManager(this@HomeScreen)
                    recyclerView.adapter = adapter
                } else {

                }
            }

            override fun onFailure(p0: Call<ListOfEntities>, p1: Throwable) {
                Log.e("RES", p1.toString());
            }
        })

    }
}