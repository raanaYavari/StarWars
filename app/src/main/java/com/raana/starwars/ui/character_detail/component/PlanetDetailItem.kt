package com.raana.starwars.ui.character_detail.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raana.starwars.mapper.toPlanetView
import com.raana.starwars.model.Info
import com.raana.starwars.model.PlanetView
import com.raana.starwars.ui.component.InfoList
import com.raana.starwars.ui.mock.MockDatabase

@Composable
fun PlanetDetailItem(modifier: Modifier = Modifier, planet: PlanetView) {
    val info = listOf(
        Info(
            "Name", planet.name
        ),
        Info(
            title = "Population",
            value = planet.population,
            icon = Icons.Default.PeopleAlt
        ),
    )
    InfoList(title = "Home World", value = info, modifier = modifier)
}

@Preview
@Composable
fun PlanetDetailItemPreview() {
    PlanetDetailItem(planet = MockDatabase.provideMockPlanetDetail().toPlanetView())
}