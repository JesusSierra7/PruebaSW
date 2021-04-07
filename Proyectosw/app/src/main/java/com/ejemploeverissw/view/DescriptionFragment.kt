package com.ejemploeverissw.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.ejemploeverissw.R
import com.ejemploeverissw.models.People
import com.ejemploeverissw.viewmodel.VMMain

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [DescriptionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DescriptionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewModel : VMMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(VMMain::class.java)
        var name = view.findViewById<TextView>(R.id.name)
        var mass = view.findViewById<TextView>(R.id.mass)
        var height = view.findViewById<TextView>(R.id.height)
        var hair_color = view.findViewById<TextView>(R.id.hair_color)
        var skin_color = view.findViewById<TextView>(R.id.skin_color)
        var eye_color = view.findViewById<TextView>(R.id.eye_color)
        var birth_year = view.findViewById<TextView>(R.id.birth_year)
        var gender = view.findViewById<TextView>(R.id.gender)

        viewModel.position.observe(requireActivity()) {
            var listPeople : ArrayList<People>? = viewModel.people.value
            var pos : Int? = viewModel.position.value
            name.text = "Name: " + pos?.let { it1 -> listPeople?.get(it1)?.name.toString() }
            mass.text = "Mass: " + pos?.let { it1 -> listPeople?.get(it1)?.mass.toString() }
            height.text = "Height: " + pos?.let { it1 -> listPeople?.get(it1)?.height.toString() }
            hair_color.text = "Hair Color: " + pos?.let { it1 -> listPeople?.get(it1)?.hair_color.toString() }
            skin_color.text = "Skin Color: " + pos?.let { it1 -> listPeople?.get(it1)?.skin_color.toString() }
            eye_color.text = "Eye Color: " + pos?.let { it1 -> listPeople?.get(it1)?.eye_color.toString() }
            birth_year.text = "Birth Year: " + pos?.let { it1 -> listPeople?.get(it1)?.birth_year.toString() }
            gender.text = "Gender: " + pos?.let { it1 -> listPeople?.get(it1)?.gender.toString() }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DescriptionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}