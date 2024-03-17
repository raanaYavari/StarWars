package com.raana.starwars.ui.character.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raana.starwars.mapper.toCharacterView
import com.raana.starwars.model.CharacterView
import com.raana.starwars.ui.mock.MockDatabase
import com.raana.starwars.ui.theme.appTypography


/**
 * CharacterItem
 *
 * @param character
 */
@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: CharacterView,
    onItemClicked: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .clickable {
                // open Character detail screen on Character item clicked
                onItemClicked()
            }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            // name and filmCount and birthYear section
            Text(
                text = character.name,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.appTypography.subtitle1,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${character.filmCount} Movies",
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.appTypography.body1,
            )
        }

        Text(
            text = character.birthYear,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.appTypography.body1,
        )
    }

}

@Preview
@Composable
fun CharacterItemPreview() {
    CharacterItem(
        character = MockDatabase.provideMockCharacterDetail().toCharacterView()
    ) {}
}