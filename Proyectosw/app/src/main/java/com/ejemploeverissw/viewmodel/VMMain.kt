package com.ejemploeverissw.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ejemploeverissw.models.People

class VMMain(application: Application) : AndroidViewModel(application) {

    val people : MutableLiveData<ArrayList<People>> by lazy {
        MutableLiveData<ArrayList<People>>()
    }

    val position : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

}
