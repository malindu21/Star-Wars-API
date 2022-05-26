package com.example.starwars.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.adapters.PlanetsAdapter
import com.example.starwars.R
import com.example.starwars.api.RetrofitInstance
import com.example.starwars.models.PlanetIcons
import com.example.starwars.models.PlanetIconsItem
import com.example.starwars.models.Planets
import com.example.starwars.models.Result
import kotlinx.android.synthetic.main.fragment_planet_list.*
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

const val TAG = "Planets"
class PlanetListFragment : Fragment(R.layout.fragment_planet_list) {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var planetList = ArrayList<Result>()
    private var imageList = ArrayList<PlanetIconsItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context)
        (activity as AppCompatActivity).supportActionBar?.title = TAG
        lifecycleScope.launchWhenCreated {
            val planets : Response<Planets>?
            val images : Response<PlanetIcons>?

            pbPlanetListFragment.visibility = View.VISIBLE
            val response = try { planets = RetrofitInstance.apiPlanets.getPlanets()
            } catch (e: IOException) {
                Toast.makeText(context,"you might not have internet connection",Toast.LENGTH_SHORT).show()
                pbPlanetListFragment.visibility = View.GONE
                return@launchWhenCreated
            } catch (e: HttpException) {
                Toast.makeText(context,"HttpException, unexpected response",Toast.LENGTH_SHORT).show()
                pbPlanetListFragment.visibility = View.GONE
                return@launchWhenCreated
            }
            if (planets.isSuccessful && planets.body() != null ) {
                planetList.clear()
                pbPlanetListFragment.visibility = View.GONE
                planetList.addAll(planets.body()!!.results)
                images = RetrofitInstance.apiImages.getImages("/v2/list?page=2&limit=${planetList.size}")
                if (images.isSuccessful && images.body() != null ) {
                    imageList.clear()
                    imageList.addAll(images.body()!!)
                    setPlanetList(planetList,imageList)
                }
            } else {
                pbPlanetListFragment.visibility = View.GONE
                Toast.makeText(context,"HttpException, unexpected response",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setPlanetList(planetList: ArrayList<Result>, imageList: ArrayList<PlanetIconsItem>) {
        Log.e("Image",imageList.size.toString())
        Log.e("Planets",planetList.size.toString())
        rvPlanets.layoutManager = LinearLayoutManager(context)
        val paidDueAdapter = PlanetsAdapter(planetList,imageList)
        rvPlanets.adapter = paidDueAdapter
        paidDueAdapter.setOnClickListener(object : PlanetsAdapter.onItemClickListener {
            override fun onPlanetClick(position: Int) {
                val fragment: Fragment = PlanetDetailsFragment()
                val fragmentManager: FragmentManager = activity!!.supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.flMain, fragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

                val bundle = Bundle().apply {
                    putString("name", planetList[position].name)
                    putString("orbital_period", planetList[position].orbital_period)
                    putString("gravity", planetList[position].gravity)
                    putString("image", imageList[position].download_url)
                }
                fragment.arguments = bundle
            }
        })
    }

}

