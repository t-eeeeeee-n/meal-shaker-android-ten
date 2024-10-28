package com.example.frontend.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.frontend.domain.GenreViewModel
import com.example.frontend.domain.LargeAreaViewModel
import com.example.frontend.domain.LargeServiceAreaViewModel
import com.example.frontend.domain.MiddleAreaViewModel
import com.example.frontend.domain.RangeViewModel
import com.example.frontend.domain.SearchViewModel
import com.example.frontend.domain.SmallAreaViewModel
import com.example.frontend.ui.components.DropdownMenu
import com.example.frontend.ui.components.ErrorMessage
import com.example.frontend.ui.components.SearchButton
import com.example.frontend.utils.Constants

@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel,
    latitude: Double,
    longitude: Double,
    rangeViewModel: RangeViewModel = viewModel(),
    genreViewModel: GenreViewModel = viewModel(),
    largeServiceAreaViewModel: LargeServiceAreaViewModel = viewModel(),
    largeAreaViewModel: LargeAreaViewModel = viewModel(),
    middleAreaViewModel: MiddleAreaViewModel = viewModel(),
    smallAreaViewModel: SmallAreaViewModel = viewModel(),
) {

    val selectedRange by rangeViewModel.selectedOption.collectAsState()
    val selectedGenre by genreViewModel.selectedOption.collectAsState()
    val selectedLargeServiceArea by largeServiceAreaViewModel.selectedOption.collectAsState()
    val selectedLargeArea by largeAreaViewModel.selectedOption.collectAsState()
    val selectedMiddleArea by middleAreaViewModel.selectedOption.collectAsState()
    val selectedSmallArea by smallAreaViewModel.selectedOption.collectAsState()

    val rangeOptions by rangeViewModel.selectOptions.collectAsState()
    val genreOptions by genreViewModel.selectOptions.collectAsState()
    val largeServiceAreaOptions by largeServiceAreaViewModel.selectOptions.collectAsState()
    val largeAreaOptions by largeAreaViewModel.selectOptions.collectAsState()
    val middleAreaOptions by middleAreaViewModel.selectOptions.collectAsState()
    val smallAreaOptions by smallAreaViewModel.selectOptions.collectAsState()

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        if (errorMessage.isNotEmpty()) {
            ErrorMessage(
                message = errorMessage,
                onDismiss = { errorMessage = "" }
            )
        } else {
            Spacer(modifier = Modifier.height(72.dp))
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f), contentAlignment = Alignment.Center) {

            SearchButton(onClick = {
                if (selectedTabIndex == 0) {
                    searchViewModel.searchByLocation(latitude.toString(),
                        longitude.toString(), selectedGenre?.name, selectedRange?.name)
                    navController.navigate("result")
                } else {
                    if (selectedLargeServiceArea == null) {
                        errorMessage = "地方を選択してください"
                    } else {
                        // 地域での検索
                        searchViewModel.searchByArea(
                            selectedLargeServiceArea?.code ?: "",
                            selectedLargeArea?.code,
                            selectedMiddleArea?.code,
                            selectedSmallArea?.code,
                            selectedGenre?.code
                        )
                        navController.navigate("result")
                    }
                }
            })
        }

        SearchView(selectedTabIndex, onTabSelected = { selectedTabIndex = it })

        when (selectedTabIndex) {
            0 -> {
                Column(modifier = Modifier) {
                    DropdownMenu(
                        label = "半径",
                        selectedItem = selectedRange?.name ?: rangeOptions.firstOrNull()?.name,
                        options = rangeOptions.map { it.name },
                        onItemSelected = { selectedName ->
                            val select = rangeOptions.firstOrNull { it.name == selectedName }
                            rangeViewModel.setOption(select)
                        },
                        unSelectedOption = false
                    )
                    DropdownMenu(
                        label = "ジャンル",
                        selectedItem = selectedGenre?.name ?: Constants.NULL_SELECT_ITEM,
                        options = genreOptions.map { it.name },
                        onItemSelected = { selectedName ->
                            val select = genreOptions.firstOrNull { it.name == selectedName }
                            genreViewModel.setOption(select)
                        }
                    )
                }
            }
            1 -> {
                Column(modifier = Modifier) {
                    DropdownMenu(
                        label = "地方",
//                        selectedItem = selectedLargeServiceArea?.name ?: largeServiceAreaOptions.firstOrNull()?.name,
                        selectedItem = selectedLargeServiceArea?.name ?: "選択してください",
                        options = largeServiceAreaOptions.map { it.name },
                        onItemSelected = { selectedName ->
                            val selectedArea = largeServiceAreaOptions.firstOrNull { it.name == selectedName }
                            largeServiceAreaViewModel.setOption(selectedArea)
                            largeAreaViewModel.onSelect(selectedArea)
                            middleAreaViewModel.clearOptions()
                            smallAreaViewModel.clearOptions()
                            largeAreaViewModel.setOption(null)
                            middleAreaViewModel.setOption(null)
                            smallAreaViewModel.setOption(null)
                        },
                        unSelectedOption = false
                    )
                    DropdownMenu(
                        label = "都道府県",
                        selectedItem = selectedLargeArea?.name ?: Constants.NULL_SELECT_ITEM,
                        options = largeAreaOptions.map { it.name },
                        onItemSelected = { selectedName ->
                            val selectedArea = largeAreaOptions.firstOrNull { it.name == selectedName }
                            largeAreaViewModel.setOption(selectedArea)
                            middleAreaViewModel.onSelect(selectedArea)
                            smallAreaViewModel.clearOptions()
                            middleAreaViewModel.setOption(null)
                            smallAreaViewModel.setOption(null)
                        },
                        hiddenFlg = selectedLargeServiceArea?.name === null
                    )
                    DropdownMenu(
                        label = "地域",
                        selectedItem = selectedMiddleArea?.name ?: Constants.NULL_SELECT_ITEM,
                        options = middleAreaOptions.map { it.name },
                        onItemSelected = { selectedName ->
                            val selectedArea = middleAreaOptions.firstOrNull { it.name == selectedName }
                            middleAreaViewModel.setOption(selectedArea)
                            smallAreaViewModel.onSelect(selectedArea)
                            smallAreaViewModel.setOption(null)
                        },
                        hiddenFlg = selectedLargeArea?.name === null
                    )
                    DropdownMenu(
                        label = "地区",
                        selectedItem = selectedSmallArea?.name ?: Constants.NULL_SELECT_ITEM,
                        options = smallAreaOptions.map { it.name },
                        onItemSelected = { selectedName ->
                            val selectedArea = smallAreaOptions.firstOrNull { it.name == selectedName }
                            smallAreaViewModel.setOption(selectedArea)
                        },
                        hiddenFlg = selectedMiddleArea?.name === null
                    )
                    DropdownMenu(
                        label = "ジャンル",
                        selectedItem = selectedGenre?.name ?: Constants.NULL_SELECT_ITEM,
                        options = genreOptions.map { it.name },
                        onItemSelected = { selectedName ->
                            val select = genreOptions.firstOrNull { it.name == selectedName }
                            genreViewModel.setOption(select)
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun SearchView(selectedTabIndex: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf("現在地で検索", "地域で検索")

    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.primary,
        indicator = { tabPositions ->
            SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .height(4.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                text = {
                    Text(
                        title,
                        fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                    )
                },
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
            )
        }
    }
}
