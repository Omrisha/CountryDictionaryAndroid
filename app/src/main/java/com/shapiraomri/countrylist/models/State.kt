package com.shapiraomri.countrylist.models

import android.os.Parcelable
import com.shapiraomri.countrylist.helpers.DynamicSearchAdapter
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class State (
    val name:String,
    val nativeName: String,
    val alpha3Code: String,
    val borders: ArrayList<String>,
    val flag: String
    ) : DynamicSearchAdapter.Searchable, Serializable {
    override fun getSearchCriteria(): String {
        return name.toLowerCase(Locale.ROOT)
    }
}