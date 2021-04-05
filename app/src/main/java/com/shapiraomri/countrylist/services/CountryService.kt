package com.shapiraomri.countrylist.services

import com.shapiraomri.countrylist.models.State
import retrofit2.Call
import retrofit2.http.GET

interface CountryService {
    @GET("all?fields=name;nativeName;borders;flag;alpha3Code;")
    fun getCountryList() : Call<List<State>>
}