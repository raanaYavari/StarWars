package com.raana.starwars.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raana.starwars.R

enum class TopBarNavigationType {
    NONE, BACK
}

@Composable
fun AppTopBar(
    title: String? = stringResource(id = R.string.app_name),
    navigationType: TopBarNavigationType = TopBarNavigationType.NONE,
    onNavigationClick: (() -> Unit)? = null,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: (@Composable () -> Unit)? = null
) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = {
                if (title != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .padding(end = 64.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h6,
                            text = title,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            },
            navigationIcon = {
                if (navigationType == TopBarNavigationType.BACK) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "ArrowBack",
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable { onNavigationClick?.invoke() }
                    )
                    return@TopAppBar
                }
                navigationIcon?.invoke()
            },
            actions = {
                actions?.invoke()
            },
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 0.dp,
        )
    }
}

@Preview
@Composable
fun AppTopBarPreview() {
    AppTopBar {

    }
}
