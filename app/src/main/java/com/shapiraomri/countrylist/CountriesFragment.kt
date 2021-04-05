package com.shapiraomri.countrylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shapiraomri.countrylist.helpers.CountriesAdapter
import com.shapiraomri.countrylist.helpers.CountriesSearchAdapter
import com.shapiraomri.countrylist.models.State
import com.shapiraomri.countrylist.services.CountryService
import com.shapiraomri.countrylist.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * A simple [Fragment] subclass.
 * Use the [CountriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountriesFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var countries: RecyclerView
    private lateinit var searchAdapter: CountriesSearchAdapter
    private val countryDictionary: HashMap<String, State> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_countries, container, false)

        activity?.title = "CountryList"
        countries = view.findViewById(R.id.country_list)

        val searchBox = view.findViewById<SearchView>(R.id.searchV)
        searchBox.setOnQueryTextListener(this)

        if (countryDictionary.isEmpty()) {
            loadFromCloud()
        } else {
            loadCountries()
        }

        return view
    }

    private fun loadCountries() {
        val countryList = ArrayList(countryDictionary.values)
        searchAdapter = CountriesSearchAdapter(countryList as MutableList<State>, View.OnClickListener {
            val name = it.findViewById<TextView>(R.id.nameTxtView).text
            val borders = countryList.firstOrNull { it.name == name }

            if (borders != null){
                val bordersList = ArrayList<State>();
                for (border in borders.borders) {
                    countryDictionary[border]?.let { it1 -> bordersList.add(it1) };
                }
                try {
                    val fragment = StateBordersFragment.newInstance(bordersList, borders.name) as Fragment

                    fragmentManager?.beginTransaction()
                            ?.addToBackStack(null)
                            ?.replace(R.id.fragment_placeholder, fragment)?.commit()

                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        })

        countries.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = searchAdapter
        }

    }

    private fun loadFromCloud() {
        val destinationService = ServiceBuilder.buildService(CountryService::class.java)
        val requestCall = destinationService.getCountryList()

        requestCall.enqueue(object : Callback<List<State>> {
            override fun onResponse(call: Call<List<State>>, response: Response<List<State>>) {
                if (response.isSuccessful) {
                    val countryList = response.body()!!

                    saveCountryListLocal(countryList)
                    loadCountries()
                } else {
                    Toast.makeText(
                            context,
                            "Something went wrong ${response.message()}",
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<State>>, t: Throwable) {
                Toast.makeText(context, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun saveCountryListLocal(countries: List<State>) {
        for (country in countries) {
            countryDictionary[country.alpha3Code] = country
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        search(p0)
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        search(p0)
        return true
    }

    private fun search(text: String?) {
        if (text != null) {
            searchAdapter.search(text.toLowerCase(Locale.ROOT)) {
                // update UI on nothing found
                Toast.makeText(context, "Nothing Found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}