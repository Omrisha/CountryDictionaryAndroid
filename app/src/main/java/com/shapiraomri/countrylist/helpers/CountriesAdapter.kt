package com.shapiraomri.countrylist.helpers

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.shapiraomri.countrylist.R
import com.shapiraomri.countrylist.StateBordersFragment
import com.shapiraomri.countrylist.models.State
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.net.URI

class CountriesAdapter(private val countriesList: List<State>, private val OnClickListener : View.OnClickListener) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.state_item, parent, false)

        view.setOnClickListener(OnClickListener)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List count: ${countriesList.size}")
        return holder.bind(countriesList[position])
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.nameTxtView)
        var nativeName = itemView.findViewById<TextView>(R.id.nativeNameTxtView)
        var imageView = itemView.findViewById<ImageView>(R.id.image_view)

        fun bind(state: State) {
            name.text = state.name
            nativeName.text = state.nativeName
            GlideToVectorYou.init()
                .with(itemView.context)
                .load(Uri.parse(state.flag), imageView)
        }

    }
}