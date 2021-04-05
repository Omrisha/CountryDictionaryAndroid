package com.shapiraomri.countrylist.services

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL = "https://restcountries.eu/rest/v2/"

    // create http client
    private val okHttp = OkHttpClient.Builder()

    // retrofit builder
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    // create retrofit instance
    private val retrofit = builder.build()

    //we will use this class to create an anonymous inner class function that
    //implements Country service Interface

    fun <T> buildService (serviceType: Class<T>) : T {
        return retrofit.create(serviceType)
    }
}