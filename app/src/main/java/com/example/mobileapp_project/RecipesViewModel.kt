package com.example.mobileapp_project
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class RecipesUIState(
    val isLoading: Boolean,
    val recipes: List<RecipeActivity.ModelRecipe>
)

class RecipesViewModel : ViewModel() {

    private val allRecipes = listOf(
        RecipeActivity.ModelRecipe(1, "Black Karaage with Curry Bento", "This Japanese modern izakaya dish features crispy black ka...", R.drawable.blackkarag),
        RecipeActivity.ModelRecipe(2, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare...", R.drawable.seafood),
        RecipeActivity.ModelRecipe(3, "Tonkotsu Ramen", "Is a Japanese noodle soup dish that originated in Fukuoka, Fu..", R.drawable.tonkotsu),
        RecipeActivity.ModelRecipe(4, "Tempura", "Is a popular Japanese dish that consists of seafood, vegetable..", R.drawable.tempura),
        RecipeActivity.ModelRecipe(5, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan. It is a ball-shaped cake..", R.drawable.takoyaki),
        RecipeActivity.ModelRecipe(6, "Yakitori Shrimp", "Is a Japanese dish that consists of skewered and grilled chicken. However, it..", R.drawable.shrimp),
        RecipeActivity.ModelRecipe(7, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare..", R.drawable.seafooddd)
    )

    private val _uiState = MutableStateFlow(RecipesUIState(isLoading = false, recipes = allRecipes))
    val uiState: StateFlow<RecipesUIState> = _uiState.asStateFlow()

    private val searchQuery = MutableStateFlow("")

    private val recipes = searchQuery
        .onEach { Log.d("QUERY", "New query: $it") }
        .filter { query -> query.isBlank() || query.length >= 3 }
        .debounce(300)
        .onEach { _uiState.update { it.copy(isLoading = true) } }
        .onEach { delay(2000) }
        .map { query ->
            allRecipes.filter { it.title.contains(query, ignoreCase = true) }
        }
        .onEach { result ->
            _uiState.update { it.copy(recipes = result, isLoading = false) }
        }

    init {
        viewModelScope.launch {
            recipes.collect()
        }
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }
}
