package com.raana.starwars.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raana.starwars.ui.theme.appTypography

/**
 * Info Item to show information by icon and title
 *
 * @param modifier
 * @param title
 * @param value
 * @param icon
 */
@Composable
fun InfoItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: ImageVector = Icons.Outlined.Info
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.padding(horizontal = 8.dp),
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colors.primary
            )
            Text(
                text = title,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.appTypography.body1,
            )
        }


        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = value,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.appTypography.subtitle1,
        )
    }

}


@Preview
@Composable
fun InfoItemPreview() {
    InfoItem(title = "height", value = "120")
}