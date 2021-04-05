package com.shapiraomri.countrylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shapiraomri.countrylist.helpers.CountriesAdapter
import com.shapiraomri.countrylist.models.State
import com.shapiraomri.countrylist.services.CountryService
import com.shapiraomri.countrylist.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = CountriesFragment()
        supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_placeholder, fragment)
                    .commit()
    }


}