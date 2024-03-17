package com.raana.starwars.ui.character_detail.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.raana.starwars.mapper.toCharacterDetailView
import com.raana.starwars.model.CharacterDetailView
import com.raana.starwars.ui.component.InfoList
import com.raana.starwars.model.Info
import com.raana.starwars.ui.mock.MockDatabase
import com.raana.starwars.R

/**
 * CharacterDetailItem to show name and Birth Year and height in cm/feet  of Character
 *
 * @param modifier
 * @param character
 */
@Composable
fun CharacterDetailItem(
    modifier: Modifier = Modifier,
    character: CharacterDetailView
) {
    val info = listOf(
        Info(
            title = stringResource(id = R.string.name),
            value = character.name,
        ),
        Info(
            title = stringResource(id = R.string.birth_year),
            value = character.birthYear,
            icon = Icons.Default.Cake
        ),
        Info(
            title = stringResource(id = R.string.height_in_cm),
            value = "${character.heightInCm}  ${stringResource(id = R.string.cm)}",
            icon = Icons.Default.Height
        ),
        Info(
            title = stringResource(id = R.string.height_in_feet),
            value = "${character.heightInFeet}  ${stringResource(id = R.string.feet)}",
            icon = Icons.Default.Height
        ),
    )

    InfoList(
        title = stringResource(id = R.string.character_detail),
        value = info,
        modifier = modifier
    )
}


@Preview
@Composable
fun CharacterDetailItemPreview() {
    CharacterDetailItem(
        character = MockDatabase.provideMockCharacterDetail().toCharacterDetailView()
    )
}