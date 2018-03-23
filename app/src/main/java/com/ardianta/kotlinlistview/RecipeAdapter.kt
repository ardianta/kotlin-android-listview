package com.ardianta.kotlinlistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

/**
 * Created by petanikode on 3/24/18.
 */

class RecipeAdapter(private val context: Context,
                    private val dataSource: ArrayList<Recipe>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // get the view from row item
        val rowView = inflater.inflate(R.layout.list_item_recipe, parent, false)

        // Get title element
        val titleTextView = rowView.findViewById<TextView>(R.id.recipe_list_title)

        // Get subtitle element
        val subtitleTextView = rowView.findViewById<TextView>(R.id.recipe_list_subtitle)

        // Get detail element
        val detailTextView = rowView.findViewById<TextView>(R.id.recipe_list_detail)

        // Get thumbnail element
        val thumbnailImageView = rowView.findViewById<ImageView>(R.id.recipe_list_thumbnail)

        val recipe = getItem(position) as Recipe

        titleTextView.text = recipe.title
        subtitleTextView.text = recipe.description
        detailTextView.text = recipe.label

        Picasso.with(context).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

}