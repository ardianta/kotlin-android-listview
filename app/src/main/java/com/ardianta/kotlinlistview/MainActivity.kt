package com.ardianta.kotlinlistview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<ListView>(R.id.recipe_list_view)
        val recipeList = Recipe.getRecipesFromFile("recipes.json", this)


        val listItems = arrayOfNulls<String>(recipeList.size)

        for(i in 0 until recipeList.size){
            val recipe = recipeList[i]
            listItems[i] = recipe.title
        }

        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        val adapter = RecipeAdapter(this, recipeList)
        listView.adapter = adapter
    }
}
