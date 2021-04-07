package com.ejemploeverissw.models

import com.google.gson.annotations.SerializedName

data class PeopleResponse(@SerializedName("results")
                          var results : ArrayList<People>,
                          @SerializedName("next")
                          var next : String)
