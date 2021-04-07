package com.ejemploeverissw.view

import MyAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.appcompat.view.menu.MenuView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ejemploeverissw.R
import com.ejemploeverissw.models.People
import com.ejemploeverissw.models.PeopleResponse
import com.ejemploeverissw.viewmodel.GetData
import com.ejemploeverissw.viewmodel.VMMain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList
import io.reactivex.Observable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment(), MyAdapter.Listener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var myAdapter: MyAdapter? = null
    private var myCompositeDisposable: CompositeDisposable? = null
    private var myPeopleArrayList: ArrayList<People>? = null
//    private var listadoCompleto: ArrayList<People>? = null
    private val BASE_URL = "https://swapi.dev/api/"
    lateinit var viewModel : VMMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(VMMain::class.java)
        myCompositeDisposable = CompositeDisposable()
        initRecyclerView()
        loadData()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MainFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    //Initialise the RecyclerView//

    private fun initRecyclerView() {

        //Use a layout manager to position your items to look like a standard ListView//
        var people_list = view?.findViewById<RecyclerView>(R.id.char_list)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        people_list?.layoutManager = layoutManager

    }

    //Implement loadData//

    private fun loadData() {
            //Define the Retrofit request//
              val requestInterface = Retrofit.Builder()

                    //Set the API’s base URL//

                    .baseUrl(BASE_URL)

                    //Specify the converter factory to use for serialization and deserialization//

                    .addConverterFactory(GsonConverterFactory.create())

                    //Add a call adapter factory to support RxJava return types//

                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    //Build the Retrofit instance//

                    .build().create(GetData::class.java)
            //Add all RxJava disposables to a CompositeDisposable//

            myCompositeDisposable?.add(requestInterface.getData(1)

                    //Send the Observable’s notifications to the main UI thread//

                    .observeOn(AndroidSchedulers.mainThread())

                    //Subscribe to the Observer away from the main UI thread//

                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse))

    }

    private fun handleResponse(peopleList: PeopleResponse) {
        var people_list = view?.findViewById<RecyclerView>(R.id.char_list)
        myPeopleArrayList = peopleList.results

        viewModel.people.value = myPeopleArrayList
        myAdapter = MyAdapter(myPeopleArrayList!!, this)
        //Set the adapter//

        people_list?.adapter = myAdapter
    }

/*    private fun sumarArray(al : ArrayList<People>) {
        for (item in al) listadoCompleto?.add(item)
    }*/

    override fun onDestroy() {
        super.onDestroy()
        //Clear all your disposables//
        myCompositeDisposable?.clear()

    }

    override fun onItemClick(ap: Int) {
        viewModel.position.value = ap
    }
}