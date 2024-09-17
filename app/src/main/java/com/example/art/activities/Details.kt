package com.example.art.activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.art.R
import com.example.art.model.Entity

class Details : AppCompatActivity() {

    private lateinit var txtTitle : TextView
    private lateinit var txtArtist : TextView
    private lateinit var txtMedium : TextView
    private lateinit var txtYear : TextView
    private lateinit var txtDescription : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findTextView()

        // Retrieve the data from intent
        val data = intent.getParcelableExtra("data", Entity::class.java)
        if (data != null) {
            setTextData(data)
        } else {
            Toast.makeText(this, "Can't retrieve data!", Toast.LENGTH_LONG).show()
        }

    }

    fun findTextView() {
        txtYear = findViewById(R.id.year)
        txtTitle = findViewById(R.id.title)
        txtArtist = findViewById(R.id.artist)
        txtMedium = findViewById(R.id.medium)
        txtDescription = findViewById(R.id.description)
    }

    fun setTextData(data : Entity) {
        this.txtTitle.setText(data.artworkTitle)
        this.txtArtist.setText(data.artist)
        this.txtYear.setText(data.year.toString())
        this.txtMedium.setText(data.medium)
        this.txtDescription.setText(data.description)
    }
}