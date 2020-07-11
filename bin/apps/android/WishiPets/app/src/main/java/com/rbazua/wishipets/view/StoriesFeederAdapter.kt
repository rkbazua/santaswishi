package com.rbazua.wishipets.view

import android.util.ArraySet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rbazua.wishipets.R
import com.rbazua.wishipets.databinding.ItemStoryBinding
import com.rbazua.wishipets.model.Story
import com.rbazua.wishipets.util.getProgressDrawable
import com.rbazua.wishipets.util.loadImage
import kotlinx.android.synthetic.main.item_story.view.*

class StoriesFeederAdapter (val storiesList: ArrayList<Story>) : RecyclerView.Adapter<StoriesFeederAdapter.StoryViewHolder>(), StoryClickListener {

    fun updateStoriesList(newStoriesList: List<Story>) {
        storiesList.addAll(newStoriesList)
        val storiesListWithoutDuplicated = storiesList.distinct()
        storiesList.clear()
        storiesList.addAll(storiesListWithoutDuplicated)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemStoryBinding>(inflater, R.layout.item_story, parent, false)
        return StoryViewHolder(view)
    }

    override fun getItemCount() = storiesList.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.view.story = storiesList.elementAt(position)
        holder.view.listener = this
    }

    override fun onStoryClicked(v: View) {
        val petuid = v.storyId.text.toString()
        val action = FeederFragmentDirections.actionStoryFragment()
        action.petuid = petuid
        Navigation.findNavController(v).navigate(action)
    }

    class StoryViewHolder(var view: ItemStoryBinding) : RecyclerView.ViewHolder(view.root)

}