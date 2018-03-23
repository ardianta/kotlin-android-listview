package com.ardianta.kotlinlistview

/**
 * Created by petanikode on 3/24/18.
 */

import android.content.Context
import org.json.JSONException
import org.json.JSONObject

class Recipe(
        val title: String,
        val description: String,
        val imageUrl: String,
        val instructionUrl: String,
        val label: String) {

    companion object {

        fun getRecipesFromFile(filename: String, context: Context): ArrayList<Recipe> {
            val recipeList = ArrayList<Recipe>()

            try {
                // Load data
                val jsonString = loadJsonFromAsset("recipes.json", context)
                val json = JSONObject(jsonString)
                val recipes = json.getJSONArray("recipes")

                // Get Recipe objects from data
                (0 until recipes.length()).mapTo(recipeList) {
                    Recipe(recipes.getJSONObject(it).getString("title"),
                            recipes.getJSONObject(it).getString("description"),
                            recipes.getJSONObject(it).getString("image"),
                            recipes.getJSONObject(it).getString("url"),
                            recipes.getJSONObject(it).getString("dietLabel"))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return recipeList
        }

        private fun loadJsonFromAsset(filename: String, context: Context): String? {
            var json: String? = null

            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charsets.UTF_8)
            } catch (ex: java.io.IOException) {
                ex.printStackTrace()
                return null
            }

            return json
        }
    }
}