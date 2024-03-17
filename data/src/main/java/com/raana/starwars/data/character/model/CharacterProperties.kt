package com.raana.starwars.data.character.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterProperties(
    val name :String,
    val height :String,
    @SerializedName("birth_year")
    val birthYear :String,
    val gender :String,
    @SerializedName("homeworld")
    val homeWorld :String,
    val url :String,
)