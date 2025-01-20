package com.example.mobileapp_project
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class RecipeActivity : AppCompatActivity() {
    data class ModelRecipe(
        val id: Int,
        val title: String,
        val description: String,
        val imageId: Int
    )

    private lateinit var viewModel: RecipesViewModel
    private lateinit var recipesAdapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        viewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)

        val recipesRecyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        recipesAdapter = RecipesAdapter(this, emptyList())
        recipesRecyclerView.layoutManager = LinearLayoutManager(this)
        recipesRecyclerView.adapter = recipesAdapter

        val searchView = findViewById<SearchView>(R.id.SearchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchRecipes(newText.orEmpty())
                return true
            }
        })

        lifecycleScope.launch {
            viewModel.filteredRecipes.collect { recipes ->
                recipesAdapter.updateData(recipes)
            }
        }
    }
}
