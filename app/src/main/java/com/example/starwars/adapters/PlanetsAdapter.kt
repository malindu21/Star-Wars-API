package com.example.starwars.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.models.PlanetIconsItem
import com.example.starwars.models.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.planets_layer.view.*

class PlanetsAdapter(private val nameList: ArrayList<Result>, private val imageList: ArrayList<PlanetIconsItem>): RecyclerView.Adapter<PlanetsAdapter.PlanetsAdapterViewHolder>() {
    private lateinit var mListener : onItemClickListener
    interface onItemClickListener{
        fun onPlanetClick(position: Int)
    }
    fun setOnClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class PlanetsAdapterViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.tvPlanetName
        val climate: TextView = itemView.tvClimate
        val image: ImageView = itemView.ivPlanetImageLayer

        init {
            itemView.setOnClickListener{
                listener.onPlanetClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetsAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.planets_layer,parent,false)
        return PlanetsAdapterViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: PlanetsAdapterViewHolder, position: Int) {
        val name = nameList[position].name
        val price = nameList[position].climate
        val image = imageList[position].download_url
        holder.name.text = "Planet Name - $name"
        holder.climate.text = "Climate - $price"
        Picasso.get().load(image).placeholder(R.drawable.ic_sw).resize(50,50).into(holder.image)
    }

    override fun getItemCount() = nameList.size
}