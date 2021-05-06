package com.example.coroutines.data.dto


import com.google.gson.annotations.SerializedName

data class PeopleDTO(
        @SerializedName("birth_year")
        var birthYear: String? = "",
        @SerializedName("created")
        var created: String? = "",
        @SerializedName("edited")
        var edited: String? = "",
        @SerializedName("eye_color")
        var eyeColor: String? = "",
        @SerializedName("films")
        var films: List<String>? = listOf(),
        @SerializedName("gender")
        var gender: String? = "",
        @SerializedName("hair_color")
        var hairColor: String? = "",
        @SerializedName("height")
        var height: String? = "",
        @SerializedName("homeworld")
        var homeworld: String? = "",
        @SerializedName("mass")
        var mass: String? = "",
        @SerializedName("name")
        var name: String? = "",
        @SerializedName("skin_color")
        var skinColor: String? = "",
        @SerializedName("species")
        var species: ArrayList<String>? = arrayListOf(),
        @SerializedName("starships")
        var starships: ArrayList<String>? = arrayListOf(),
        @SerializedName("url")
        var url: String? = "",
        @SerializedName("vehicles")
        var vehicles: ArrayList<String>? = arrayListOf()
) {
    override fun toString(): String {
        return "PeopleDTO(birthYear=$birthYear, created=$created, edited=$edited, eyeColor=$eyeColor, films=$films, gender=$gender, hairColor=$hairColor, height=$height, homeworld=$homeworld, mass=$mass, name=$name, skinColor=$skinColor, species=$species, starships=$starships, url=$url, vehicles=$vehicles)"
    }
}