package com.shapiraomri.countrylist.helpers

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.shapiraomri.countrylist.R
import com.shapiraomri.countrylist.models.State

class BordersAdapter(private val borders: List<State>) :
    RecyclerView.Adapter<BordersAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.nameTxtView)
        var nativeName = itemView.findViewById<TextView>(R.id.nativeNameTxtView)
        var imageView = itemView.findViewById<ImageView>(R.id.image_view)

        fun bind (state: State) {
            name.text = state.name
            nativeName.text = state.nativeName
            GlideToVectorYou.init()
                    .with(itemView.context)
                    .load(Uri.parse(state.flag), imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.state_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(borders[position])
    }

    override fun getItemCount(): Int {
        return borders.size
    }
}