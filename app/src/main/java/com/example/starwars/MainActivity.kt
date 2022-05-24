package com.example.starwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.fragments.PlanetListFragment
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.HttpException
import java.io.IOException



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flMain, PlanetListFragment())
            addToBackStack(null)
            commit()
        }
    }
}