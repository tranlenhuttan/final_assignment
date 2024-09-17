package com.example.art.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.art.R
import com.example.art.activities.Details
import com.example.art.model.Entity
import com.example.art.model.ListOfEntities

class EntityAdapter(private var data : ListOfEntities) :
    RecyclerView.Adapter<EntityAdapter.EntityItem>() {

    class EntityItem(view: View) : RecyclerView.ViewHolder(view) {
        val artist : TextView
        val medium : TextView
        val title: TextView
        val year : TextView

        init {
            artist = view.findViewById(R.id.artist)
            medium = view.findViewById(R.id.medium)
            title = view.findViewById(R.id.title)
            year = view.findViewById(R.id.year)
        }

        fun bind(data : Entity) {
            this.itemView.setOnClickListener {
                val context = this.itemView.context
                val intent = Intent(context, Details::class.java)
                intent.putExtra("data", data)
                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_entity_item, parent, false)
        return EntityItem(view)
    }

    override fun getItemCount(): Int {
       return data.entityTotal
    }

    override fun onBindViewHolder(holder: EntityItem, position: Int) {
        holder.title.setText(data.entities[position].artworkTitle)
        holder.medium.setText(data.entities[position].medium)
        holder.artist.setText(data.entities[position].artist)
        holder.year.setText(data.entities[position].year.toString())
        holder.bind(data.entities[position])
    }

}