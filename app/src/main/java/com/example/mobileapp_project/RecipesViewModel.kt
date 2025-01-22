package com.example.mobileapp_project
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {

    val allRecipes = listOf(
        RecipeActivity.ModelRecipe(1, "Black Karaage with Curry Bento", "This Japanese modern izakaya dish features crispy black ka...", R.drawable.blackkarag),
        RecipeActivity.ModelRecipe(2, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare...", R.drawable.seafood),
        RecipeActivity.ModelRecipe(3, "Tonkotsu Ramen", "Is a Japanese noodle soup dish that originated in Fukuoka, Fu..", R.drawable.tonkotsu),
        RecipeActivity.ModelRecipe(4, "Tempura", "Is a popular Japanese dish that consists of seafood, vegetable..", R.drawable.tempura),
        RecipeActivity.ModelRecipe(5, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan. It is a ball-shaped cake..", R.drawable.takoyaki),
        RecipeActivity.ModelRecipe(6, "Yakitori Shrimp", "Is a Japanese dish that consists of skewered and grilled chicken. However, it..", R.drawable.shrimp),
        RecipeActivity.ModelRecipe(7, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare..", R.drawable.seafooddd)
    )
    private val _filteredRecipes = MutableStateFlow<List<RecipeActivity.ModelRecipe>>(allRecipes)
    val filteredRecipes: StateFlow<List<RecipeActivity.ModelRecipe>> = _filteredRecipes.asStateFlow()

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            if (query.length < 3) {
                _filteredRecipes.update { allRecipes }
            } else {
                val filtered = allRecipes.filter {
                    it.title.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
                }
                if (filtered != _filteredRecipes.value) {
                    _filteredRecipes.update { filtered }
                }
            }
        }
    }
}
