package com.ejemploeverissw.viewmodel
import android.telecom.Call
import com.ejemploeverissw.models.People
import com.ejemploeverissw.models.PeopleResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GetData {

    //Describe the request type and the relative URL//
    @GET("people/")
    fun getData(@Query("page") numPag : Int) : Observable<PeopleResponse>
}