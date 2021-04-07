package com.ejemploeverissw.view

import MyAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.ejemploeverissw.R

import io.reactivex.disposables.CompositeDisposable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ejemploeverissw.models.People
import com.ejemploeverissw.models.PeopleResponse
import com.ejemploeverissw.viewmodel.GetData
import com.ejemploeverissw.viewmodel.VMMain

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit

import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : VMMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(VMMain::class.java)
        viewModel.position.observe(this) {
            supportFragmentManager.beginTransaction().replace(
                R.id.frag,
                DescriptionFragment(), null
            ).addToBackStack(null).commit()
        }
    }

}

