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
import androidx.recyclerview.widget.LinearLayoutManager

import com.rbazua.wishipets.R
import com.rbazua.wishipets.viewmodel.FeederViewModel
import kotlinx.android.synthetic.main.fragment_feeder.*

class FeederFragment : Fragment() {

    private lateinit var viewModel: FeederViewModel
    private val storiesFeederAdapter = StoriesFeederAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feeder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeederViewModel::class.java)
        viewModel.refresh()

        storiesFeeder.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = storiesFeederAdapter
        }

        refreshLayout.setOnRefreshListener {
            storiesFeeder.visibility = View.GONE
            feederError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refreshBypassCache()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.stories.observe(this, Observer {stories ->
            stories?.let {
                storiesFeeder.visibility = View.VISIBLE
                storiesFeederAdapter.updateStoriesList(stories)
            }
        })

        viewModel.storiesLoadError.observe(this, Observer{isError ->
            isError?.let {
                feederError.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer{isLoading ->
            isLoading?.let {
                loadingView.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                    feederError.visibility = View.GONE
                    storiesFeeder.visibility = View.GONE
                }
            }
        })
    }
}
