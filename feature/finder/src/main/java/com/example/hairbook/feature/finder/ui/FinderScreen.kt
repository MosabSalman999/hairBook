package com.example.hairbook.feature.finder.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hairbook.core.ui.component.HairBookTopBar
import com.example.hairbook.feature.finder.R

@Composable
fun FinderScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { HairBookTopBar(title = stringResource(R.string.finder_title)) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = stringResource(R.string.finder_title), style = MaterialTheme.typography.titleLarge)
            Text(text = stringResource(R.string.finder_placeholder), style = MaterialTheme.typography.bodyMedium)
        }
    }
}
