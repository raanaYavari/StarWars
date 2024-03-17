package com.raana.starwars.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raana.starwars.model.Info
import com.raana.starwars.ui.mock.MockDatabase
import com.raana.starwars.ui.theme.appTypography

/**
 * Info List to show information by icon and title
 *
 * @param modifier
 * @param title
 * @param value
 */
@Composable
fun InfoList(
    modifier: Modifier = Modifier,
    title: String,
    value: List<Info>,
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = MaterialTheme.colors.background,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = title,
            style = MaterialTheme.appTypography.body1,
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            value.forEach {
                InfoItem(
                    title = it.title,
                    value = it.value,
                    icon = it.icon
                )
            }
        }
    }

}

@Preview
@Composable
fun InfoItemListPreview() {
    InfoList(title = "height", value = arrayListOf(MockDatabase.provideInfo()))
}