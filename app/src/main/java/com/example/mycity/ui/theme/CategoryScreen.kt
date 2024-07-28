package com.example.mycity.ui.theme


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycity.R
import com.example.mycity.data.Categories
import com.example.mycity.data.CategoriesList
import com.example.mycity.models.CategoryListViewModel
import com.example.mycity.windowUtils.CategoryContentType


@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    onBackPress: () -> Unit,
) {
    val viewModel: CategoryListViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact -> CategoryContentType.List
        WindowWidthSizeClass.Medium -> CategoryContentType.ListAndDetail
        WindowWidthSizeClass.Expanded -> CategoryContentType.ListAndDetail
        else -> CategoryContentType.List
    }
    Scaffold(
        topBar = {
            CityAppBar(
                onBackButtonClick = { viewModel.navigateToCategoryList() },
                isShowingListPage = uiState.isShowingListPage ,
                windowSize = windowSize
            )
        }
    ) { innerPadding ->
        if (contentType == CategoryContentType.ListAndDetail) {
            CategoryListAndDetail(
                categories = uiState.categories,
                onClick = { viewModel.updateCurrentCategory(it) },
                selectedCategory = uiState.currentCategory,
                onBackPressed = onBackPress,
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxWidth()
            )
        }
        else {
            if (uiState.isShowingListPage){
            CategoriesList(
                categories = CategoriesList.getCategoryData(),
                onClick = {
                    viewModel.updateCurrentCategory(it)
                    viewModel.navigateToDetailPage() },
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                contentPadding = innerPadding
            )
        } else  {
                CategoriesDetail(
                    selectedCategory = uiState.currentCategory,
                    onBackPressed = { viewModel.navigateToCategoryList() },
                    contentPadding = innerPadding
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityAppBar(
    onBackButtonClick: () -> Unit,
    isShowingListPage: Boolean,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val isShowingDetailPage = windowSize != WindowWidthSizeClass.Expanded && !isShowingListPage
    TopAppBar(
        title = {
            Text(
                text =
                if (isShowingDetailPage) {
                    stringResource(R.string.detail_fragment_label)
                } else {
                    stringResource(R.string.list_fragment_label)
                },
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = if (isShowingDetailPage) {
            {
                IconButton(onClick = onBackButtonClick, colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                    )
                }
            }
        } else {
            { Box {} }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
    )
}


@Composable
private fun CategoryListAndDetail(
    categories: List<Categories>,
    selectedCategory: Categories,
    onClick: (Categories) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Row(
        modifier = modifier
            .fillMaxSize()
    ) {
        CategoriesList(
            categories = categories,
            onClick = onClick,
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding(),
            ),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )
        CategoriesDetail(
            selectedCategory = selectedCategory,
            onBackPressed = onBackPressed,
                    contentPadding = PaddingValues(
                        top = contentPadding.calculateTopPadding(),
                    ),
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )

    }

}
@Composable
private fun CategoriesList(
    categories: List<Categories>,
    onClick: (Categories) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
    ) {
        items(categories, key = { categories -> categories.id}){ categories->
            CategoriesListItem(
                categories = categories,
                onItemClick = onClick
            )
        }
    }
}

@Composable
private fun CategoriesListItem(
    categories: Categories,
    onItemClick: (Categories) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier.height(dimensionResource(R.dimen.card_image_height)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(categories)}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            CategoriesListImageItem(
                categories = categories,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column (
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {
                Text(
                    text = categories.categoryName,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Text(
                    text = categories.categoryName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
            }
        }
    }
}
@Composable
private fun CategoriesListImageItem(categories: Categories, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(categories.image),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
    }
}



@Composable
private fun CategoriesDetail(
    selectedCategory: Categories,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(
                top = contentPadding.calculateTopPadding(),
                bottom = contentPadding.calculateBottomPadding()
            )

    )  {

        val layoutDirection = LocalLayoutDirection.current
        Box(
            modifier = modifier


        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
//                    .padding(
//                        bottom = contentPadding.calculateTopPadding(),
//                        start = contentPadding.calculateStartPadding(layoutDirection),
//                        end = contentPadding.calculateEndPadding(layoutDirection)
//                    )
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Box {
                        Image(
                            modifier = Modifier
                                .height(dimensionResource(R.dimen.image_height))
                                .verticalScroll(rememberScrollState()),
                            painter = painterResource(selectedCategory.image),
                            contentDescription = null,
                            alignment = Alignment.TopCenter,
                            contentScale = ContentScale.FillWidth,
                        )
                    }
                    Column(
                        Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                                    0f,
                                    400f
                                )
                            )
                    ) {
                        Text(
                            text = selectedCategory.categoryName,
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                            modifier = Modifier
                                .padding(horizontal = dimensionResource(R.dimen.padding_small))
                        )

                    }

                }
                when (selectedCategory.categoryName) {
                    "Monuments" -> {
                        //Monuments List
                        LazyColumn {
                            items(CategoriesList.monumentsList) { monuments ->
                                MonumentsItem(monuments = monuments)
                            }
                        }

                    }

                    "Parks" -> {
                        //Parks List
                        LazyColumn {
                            items(CategoriesList.parksList) { parks ->
                                ParksItem(parks = parks)
                            }
                        }

                    }

                    "Forts" -> {
                        //Forts List
                        LazyColumn {
                            items(CategoriesList.fortsList) { forts ->
                                FortsItem(forts = forts)
                            }
                        }
                    }

                    "Temples" -> {
                        //Temples List
                        LazyColumn {
                            items(CategoriesList.templesList) { temples ->
                                TemplesItem(temples = temples)
                            }
                        }
                    }
                }
            }



        }



        

    }
}

@Preview(device = Devices.TABLET)
@Composable
fun SportsListAndDetailsPreview() {
   MyCityTheme {
        Surface {
            CategoryListAndDetail(
                categories = CategoriesList.getCategoryData(),
                selectedCategory = CategoriesList.getCategoryData().getOrElse(0){
                    CategoriesList.defaultCategory
                },
                onClick = {},
                onBackPressed = {},
            )
        }
    }
}
@Preview
@Composable
fun CategoriesListPreview() {
    MyCityTheme {
        Surface {
            CategoriesList(
                categories = CategoriesList.getCategoryData(),
                onClick = {},
            )
        }
    }
}
@Preview
@Composable
fun CategoriesDetailPreview() {
    MyCityTheme {
        Surface {
            CategoriesDetail(
                selectedCategory = CategoriesList.getCategoryData().getOrElse(0){
                    CategoriesList.defaultCategory
                },
                onBackPressed = {},
                contentPadding = PaddingValues(0.dp))
        }
    }
}