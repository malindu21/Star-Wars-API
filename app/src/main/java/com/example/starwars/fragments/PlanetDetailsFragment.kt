package com.example.starwars.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.starwars.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_planet_details.*

const val DETAILS = "Planet Details"
class PlanetDetailsFragment : Fragment(R.layout.fragment_planet_details) {
    private var name: String = ""
    private var orbitalPeriod: String = ""
    private var gravity: String = ""
    private var image: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = DETAILS
        this.arguments?.let {
            name = it.getString("name", "")
            orbitalPeriod = it.getString("orbital_period", "")
            gravity = it.getString("gravity", "")
            image = it.getString("image", "")
            planetDetailsMain(name,orbitalPeriod,gravity,image)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun planetDetailsMain(
        name: String?,
        orbitalPeriod: String?,
        gravity: String?,
        image: String?
    ) {
        tvName.text = "Name - $name"
        tvOrbitalPeriod.text = "Orbital Period - $orbitalPeriod"
        tvGravity.text = "Gravity - $gravity"
        Picasso.get().load(image).placeholder(R.drawable.ic_sw).resize(200,200).into(ivPlanetImage)
        Picasso.get().load(image).placeholder(R.drawable.ic_white).resize(1500,1500).into(ivPlanetImageMain)
    }
}