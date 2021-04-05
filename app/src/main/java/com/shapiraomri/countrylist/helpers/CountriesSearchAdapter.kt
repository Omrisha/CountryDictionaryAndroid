package com.shapiraomri.countrylist.helpers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shapiraomri.countrylist.R
import com.shapiraomri.countrylist.models.State

class CountriesSearchAdapter(private val mutableList: MutableList<State>,
                             private val OnClickListener : View.OnClickListener) :
    DynamicSearchAdapter<State>(mutableList) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.state_item, parent, false)

        view.setOnClickListener(OnClickListener)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mutableList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List count: ${mutableList.size}")
        return holder.bind(mutableList[position])
    }
}