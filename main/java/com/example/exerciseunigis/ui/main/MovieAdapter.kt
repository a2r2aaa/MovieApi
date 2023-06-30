package com.example.exerciseunigis.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MovieAdapter(context: Context, movies: List<Movie>) : ArrayAdapter<Movie>(context, 0, movies) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder

        val view = if (convertView == null) {
            val newView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)
            viewHolder = ViewHolder()
            viewHolder.titleTextView = newView.findViewById(android.R.id.text1)
            viewHolder.overviewTextView = newView.findViewById(android.R.id.text2)
            newView.tag = viewHolder
            newView
        } else {
            viewHolder = convertView.tag as ViewHolder
            convertView
        }

        val movie = getItem(position)!!
        viewHolder.titleTextView.text = movie.title
        viewHolder.overviewTextView.text = movie.overview

        return view
    }

    private class ViewHolder {
        lateinit var titleTextView: TextView
        lateinit var overviewTextView: TextView
    }
}