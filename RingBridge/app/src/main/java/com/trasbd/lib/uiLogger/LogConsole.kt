package com.trasbd.lib.uiLogger

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp




@Composable
fun LogConsole(
    logs: List<LogLine>, modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    LaunchedEffect(logs.size) {
        if (logs.isNotEmpty()) {
            listState.animateScrollToItem(logs.lastIndex)
        }
    }

    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text("Console", style = MaterialTheme.typography.titleMedium)

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            LazyColumn(
                state = listState, modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {

                items(logs) { line: LogLine ->
                    Text(
                        text = "[${line.time}] ${line.level}: ${line.message}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
