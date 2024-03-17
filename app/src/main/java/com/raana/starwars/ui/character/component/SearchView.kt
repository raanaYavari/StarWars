package com.raana.starwars.ui.character.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.raana.starwars.ui.theme.spacing
import com.raana.starwars.R

/**
 * SearchView Component
 *
 * @param modifier
 * @param searchText
 */
@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    searchText: TextFieldValue,
    onSearchTextChange: (TextFieldValue) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                onSearchTextChange(it)
            },
            placeholder = { Text(stringResource(id = R.string.search)) },
            leadingIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "search",
                    tint = MaterialTheme.colors.onBackground
                )
            },
            shape = MaterialTheme.shapes.small,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                disabledBorderColor = MaterialTheme.colors.background,
            ),
            singleLine = true,
            trailingIcon = {
                if (searchText.text.isNotEmpty())
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear text",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .clickable {
                                onSearchTextChange(TextFieldValue())
                            }
                    )
            },
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun SearchViewPreview() {
    val searchText by remember { mutableStateOf(TextFieldValue("")) }
    SearchView(searchText = searchText) {}
}