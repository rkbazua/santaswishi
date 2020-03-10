package com.rbazua.wishipets.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.rbazua.wishipets.R
import com.rbazua.wishipets.databinding.FragmentStoryDetailBinding
import com.rbazua.wishipets.util.getProgressDrawable
import com.rbazua.wishipets.util.loadImage
import com.rbazua.wishipets.viewmodel.StoryDetailViewModel
import kotlinx.android.synthetic.main.fragment_story_detail.*
import kotlinx.android.synthetic.main.item_story.*

class StoryDetailFragment : Fragment() {

    private lateinit var viewModel : StoryDetailViewModel
    private var petuid = "0"

    private lateinit var dataBinding: FragmentStoryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_story_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(StoryDetailViewModel::class.java)
        arguments?.let {
            petuid = StoryDetailFragmentArgs.fromBundle(it).petuid
        }
        viewModel.fetch(petuid)

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.storyLiveData.observe(this, Observer { story ->
            story?.let {
                dataBinding.story = story
            }
        })
    }


}
