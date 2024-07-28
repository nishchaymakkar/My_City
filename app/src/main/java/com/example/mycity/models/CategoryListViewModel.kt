package com.example.mycity.models

import androidx.lifecycle.ViewModel
import com.example.mycity.data.Categories
import com.example.mycity.data.CategoriesList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CategoryListViewModel: ViewModel() {

        private val _uiState = MutableStateFlow(CategoryListUiState(
            categories = CategoriesList.getCategoryData(),
            currentCategory = CategoriesList.getCategoryData().getOrElse(0) {
                CategoriesList.defaultCategory
            }
        )
    )
    val uiState : StateFlow<CategoryListUiState> = _uiState

    fun updateCurrentCategory(selectedCategory: Categories) {
        _uiState.update {
            it.copy(currentCategory = selectedCategory)
        }
    }

    fun navigateToDetailPage() {
        _uiState.update {
        it.copy(isShowingListPage = false) }
    }
    fun navigateToCategoryList() {
        _uiState.update { it.copy(isShowingListPage = true) }
    }
}


data class CategoryListUiState (
    val categories:List<Categories> = emptyList(),
    val currentCategory: Categories = CategoriesList.defaultCategory,
    var isShowingListPage: Boolean = true,

    )
