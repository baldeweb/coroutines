package com.example.coroutines.data.dto


import com.google.gson.annotations.SerializedName

data class PlanetDTO(
    @SerializedName("climate")
    var climate: String? = "",
    @SerializedName("created")
    var created: String? = "",
    @SerializedName("diameter")
    var diameter: String? = "",
    @SerializedName("edited")
    var edited: String? = "",
    @SerializedName("films")
    var films: ArrayList<String>? = arrayListOf(),
    @SerializedName("gravity")
    var gravity: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("orbital_period")
    var orbitalPeriod: String? = "",
    @SerializedName("population")
    var population: String? = "",
    @SerializedName("residents")
    var residents: ArrayList<Any>? = arrayListOf(),
    @SerializedName("rotation_period")
    var rotationPeriod: String? = "",
    @SerializedName("surface_water")
    var surfaceWater: String? = "",
    @SerializedName("terrain")
    var terrain: String? = "",
    @SerializedName("url")
    var url: String? = ""
)