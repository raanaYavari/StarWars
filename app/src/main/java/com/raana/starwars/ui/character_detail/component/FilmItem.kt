package com.raana.starwars.ui.character_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raana.starwars.mapper.toFilmView
import com.raana.starwars.model.FilmView
import com.raana.starwars.ui.mock.MockDatabase
import com.raana.starwars.ui.theme.appTypography

@Composable
fun FilmList(films: List<FilmView>, modifier: Modifier = Modifier) {
    Column(
         modifier = modifier
    ) {
        films.forEach {
            FilmItem(film = it)
        }
    }
}

@Composable
fun FilmItem(film: FilmView, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(shape = MaterialTheme.shapes.small, color = MaterialTheme.colors.surface)
            .padding(8.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.padding(8.dp),
                imageVector = Icons.Default.Movie,
                contentDescription = "Movie",
                tint = MaterialTheme.colors.primary
            )
            Text(
                text = film.title,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.appTypography.subtitle1,
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            text = film.openingCrawl,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.appTypography.body1,
        )
    }
}

@Preview
@Composable
fun FilmItemPreview() {
    FilmItem(film = MockDatabase.provideMockFilmDetail().toFilmView())
}