package com.example.achievements_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.achievements_app.ui.theme.Achievements_appTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Achievements_appTheme {
                AchievementsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AchievementsScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Badges",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        ContentView(list = MyBadges.getAllBadges(), innerPadding = innerPadding)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ContentView(list: List<ItemData>, innerPadding: PaddingValues) {
    LazyVerticalStaggeredGrid(
        state = rememberLazyStaggeredGridState(),
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .padding(innerPadding),
        contentPadding = PaddingValues(10.dp),
    ) {
        itemsIndexed(
            items = list,
            key = { _: Int, item: ItemData ->
                item.hashCode()
            }
        ) { _, item ->
            ItemView(itemData = item)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ItemView(itemData: ItemData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = itemData.imageId),
                contentDescription = itemData.description,
                modifier = Modifier
                    .padding(20.dp)
            )
            Text(
                text = itemData.description,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}