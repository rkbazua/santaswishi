package com.rbazua.wishipets.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.rbazua.wishipets.R
import com.rbazua.wishipets.viewmodel.StoryDetailViewModel
import kotlinx.android.synthetic.main.fragment_story_detail.*
import kotlinx.android.synthetic.main.item_story.*

class StoryDetailFragment : Fragment() {

    private lateinit var viewModel : StoryDetailViewModel
    private var petuid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_story_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(StoryDetailViewModel::class.java)
        viewModel.fetch()

        arguments?.let {
            petuid = StoryDetailFragmentArgs.fromBundle(it).petuid
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.storyLiveData.observe(this, Observer { story ->
            story?.let {
                detailStoryTitleText.text = story.title
                detailStoryDescriptionText.text = story.description
            }
        })
    }


}
