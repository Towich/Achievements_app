package com.example.achievements_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
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
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var currBadge by remember { mutableStateOf(ItemData(0, 0, "null", "null", "null")) }
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

        // LazyVerticalStaggeredGrid with two columns
        ContentView(
            list = MyBadges.getAllBadges(),
            innerPadding = innerPadding,
            onItemClicked = { itemData ->
                currBadge = itemData
                showBottomSheet = true
            }
        )

        // BottomSheet with description of Badge
        if (showBottomSheet)
            BadgeBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                currBadge = currBadge
            )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ContentView(
    list: List<ItemData>,
    innerPadding: PaddingValues,
    onItemClicked: (ItemData) -> Unit
) {
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
            ItemView(
                itemData = item,
                onClicked = onItemClicked
            )
        }
    }
}

@Composable
private fun ItemView(
    itemData: ItemData,
    onClicked: (ItemData) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClicked(itemData) },
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
                contentDescription = itemData.name,
                modifier = Modifier
                    .padding(20.dp)
            )
            Text(
                text = itemData.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BadgeBottomSheet(
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    currBadge: ItemData
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .height(400.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Image of Badge
                Image(
                    modifier = Modifier
                        .size(150.dp),
                    painter = painterResource(id = currBadge.imageId),
                    contentDescription = currBadge.name,
                    contentScale = ContentScale.Crop
                )

                // Text of Badge
                Text(
                    text = currBadge.name,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(top = 15.dp)
                )
            }

            // Text of Description
            Text(
                text = currBadge.description,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
            )

            // Text of Earned date
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Earned: ${currBadge.earnedDate}",
                    color = Color(0xFF949494),
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .padding(end = 15.dp)
                )
            }
        }

    }
}