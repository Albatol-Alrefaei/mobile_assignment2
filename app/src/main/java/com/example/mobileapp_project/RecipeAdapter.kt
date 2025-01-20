package com.example.mobileapp_project
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecipesAdapter(private val context: Context, private var recipesList: List<RecipeActivity.ModelRecipe>) : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipes = recipesList[position]
        holder.bind(recipes)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Clicked on ${recipes.title}", Toast.LENGTH_SHORT).show()
        }

        holder.shareBtn.setOnClickListener {
            Toast.makeText(context, "Clicked on share ${recipes.title}", Toast.LENGTH_SHORT).show()
        }

        holder.likeBtn.setOnClickListener {
            Toast.makeText(context, "Clicked on like ${recipes.title}", Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int {
        return recipesList.size
    }
    fun updateData(newRecipes: List<RecipeActivity.ModelRecipe>) {
        recipesList = newRecipes
        notifyDataSetChanged()
    }

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.title)
        private val desc: TextView = view.findViewById(R.id.desc)
        private val image: ImageView = view.findViewById(R.id.image)
        val shareBtn: ImageButton = view.findViewById(R.id.shareBtn)
        val likeBtn: ImageButton = view.findViewById(R.id.likeBtn)

        fun bind(recipe: RecipeActivity.ModelRecipe) {
            title.text = recipe.title
            desc.text = recipe.description
            image.setImageResource(recipe.imageId)
        }
    }
}