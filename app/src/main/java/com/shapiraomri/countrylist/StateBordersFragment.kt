package com.shapiraomri.countrylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shapiraomri.countrylist.helpers.BordersAdapter
import com.shapiraomri.countrylist.helpers.CountriesAdapter
import com.shapiraomri.countrylist.models.State

private const val ARG_PARAM1 = "statesArray"
private const val ARG_PARAM2 = "title"

/**
 * A simple [Fragment] subclass.
 * Use the [StateBordersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StateBordersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var statesList: List<State>? = null
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            statesList = it.getSerializable(ARG_PARAM1) as ArrayList<State>
            title = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_state_borders, container, false)

        activity?.title  = title
        val states = view.findViewById<RecyclerView>(R.id.states)

        val noDataText = view.findViewById<TextView>(R.id.empty_view)

        if (statesList?.isEmpty() == true){
            states.setVisibility(View.GONE)
            noDataText.setVisibility(View.VISIBLE)
        }
        else {
            states.setVisibility(View.VISIBLE)
            noDataText.setVisibility(View.GONE)
        }
        states.apply {
            layoutManager = GridLayoutManager(view.context, 1)
            adapter = statesList?.let { BordersAdapter(it) }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StateBordersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<State>, param2: String) =
            StateBordersFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}