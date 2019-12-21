package com.rbazua.wishipets.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rbazua.wishipets.R
import com.rbazua.wishipets.model.Story
import kotlinx.android.synthetic.main.item_story.view.*

class StoriesFeederAdapter (val storiesList: ArrayList<Story>) : RecyclerView.Adapter<StoriesFeederAdapter.StoryViewHolder>() {

    class StoryViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    fun updateStoriesList(newStoriesList: List<Story>) {
        storiesList.clear()
        storiesList.addAll(newStoriesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_story, parent,false)
        return StoryViewHolder(view)
    }

    override fun getItemCount() = storiesList.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.view.titleText.text = storiesList[position].title
        holder.view.descriptionText.text = storiesList[position].description
        holder.view.setOnClickListener{
            Navigation.findNavController(it).navigate(FeederFragmentDirections.actionStoryFragment())
        }
    }
}