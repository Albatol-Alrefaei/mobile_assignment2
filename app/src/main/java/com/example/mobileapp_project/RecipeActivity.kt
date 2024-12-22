package com.example.mobileapp_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecipeActivity : AppCompatActivity() {
    data class ModelRecipe(
        val id: Int,
        val title: String,
        val description: String,
        val imageId: Int
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val recipes = listOf(
            ModelRecipe(1, "Black Karaage with Curry Bento", "This Japanese modern izakaya dish features crispy black ka...", R.drawable.blackkarag),
            ModelRecipe(2, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare...", R.drawable.seafood),
            ModelRecipe(3, "Tonkotsu Ramen", "Is a Japanese noodle soup dish that originated in Fukuoka, Fu..", R.drawable.tonkotsu),
            ModelRecipe(4, "Tempura", "Is a popular Japanese dish that consists of seafood, vegetable..", R.drawable.tempura),
            ModelRecipe(5, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan. It is a ball-shaped cake..", R.drawable.takoyaki),
            ModelRecipe(6, "Yakitori Shrimp", "Is a Japanese dish that consists of skewered and grilled chicken. However, it..", R.drawable.shrimp),
            ModelRecipe(7, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare..", R.drawable.seafooddd)
        )

        val recipesRecyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        recipesRecyclerView.layoutManager = LinearLayoutManager(this)
        recipesRecyclerView.adapter = RecipesAdapter(this, recipes)
    }
}