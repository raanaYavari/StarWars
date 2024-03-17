package com.raana.starwars.domain.character.result

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * CharacterDetail object
 *
 * @property CharacterDetail
 * @constructor Create empty Character object
 */
@Parcelize
@Serializable
data class CharacterDetail(
    val url: String,
    val name: String,
    val birthYear: String="",
    val height: String,
    val homeWorldUrl: String="",
    val films: List<String>,
    val species: List<String>,
) : Parcelable