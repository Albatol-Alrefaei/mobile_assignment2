package com.example.mobileapp_project
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapp_project.RecipesAdapter
import com.example.mobileapp_project.RecipesViewModel
import kotlinx.coroutines.launch

class RecipeActivity : AppCompatActivity() {
    data class ModelRecipe(
        val id: Int,
        val title: String,
        val description: String,
        val imageId: Int
    )

    private lateinit var credentialsManager: CredentialsManager
    private lateinit var viewModel: RecipesViewModel
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        viewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)
        val recipesRecyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        progressBar = findViewById(R.id.progressBar)
        recipesAdapter = RecipesAdapter(this, emptyList())
        recipesRecyclerView.layoutManager = LinearLayoutManager(this)
        recipesRecyclerView.adapter = recipesAdapter
        logoutButton = findViewById(R.id.logoutBtn)
        logoutButton.setOnClickListener {
            logoutUser()
        }
        val searchView = findViewById<SearchView>(R.id.SearchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateSearchQuery(newText.orEmpty())
                return true
            }
        })

        progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                recipesAdapter.updateData(state.recipes)
            }
        }

        viewModel.updateSearchQuery("")
        credentialsManager = CredentialsManager.getInstance()
        lifecycleScope.launch {
            credentialsManager.loginState.collect { isLoggedIn ->
                if (!isLoggedIn) {
                    val intent = Intent(this@RecipeActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

        }
    }
    private fun logoutUser() {
        CredentialsManager.getInstance().logout()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}


