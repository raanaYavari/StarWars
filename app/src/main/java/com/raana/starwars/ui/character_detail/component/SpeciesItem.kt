package com.raana.starwars.ui.character_detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raana.starwars.mapper.toSpeciesView
import com.raana.starwars.model.Info
import com.raana.starwars.model.SpeciesView
import com.raana.starwars.ui.component.InfoList
import com.raana.starwars.ui.mock.MockDatabase


@Composable
fun SpeciesList(species: List<SpeciesView>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        species.forEach {
            SpeciesItem(species = it)
        }
    }
}

@Composable
fun SpeciesItem(species: SpeciesView, modifier: Modifier = Modifier) {
    val info = listOf(
        Info(
            "Name", species.name
        ),
        Info(
            title = "Language",
            value = species.language ?: "Unknown",
            icon = Icons.Default.Language
        ),
    )
    InfoList(title = "Species Detail", value = info, modifier = modifier)
}

@Preview
@Composable
fun SpeciesItemPreview() {
    SpeciesItem(species = MockDatabase.provideMockSpeciesDetail().toSpeciesView())
}