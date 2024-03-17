package com.raana.starwars.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterView(
    val name: String,
    val filmCount: Int,
    val birthYear: String,
    val url: String,
) : Parcelable