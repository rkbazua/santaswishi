package com.rbazua.wishipets.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feeder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeederViewModel::class.java)
        viewModel.refreshBypassCache()
        val rvLayoutManager = LinearLayoutManager(context);

        storiesFeeder.apply {
            layoutManager = rvLayoutManager
            adapter = storiesFeederAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = rvLayoutManager.getChildCount();
                    val totalItemCount = rvLayoutManager.getItemCount();
                    val pastVisibleItems = rvLayoutManager.findFirstVisibleItemPosition();
                    if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                        feederError.visibility = View.GONE
                        viewModel.refreshBypassCache()
                        refreshLayout.isRefreshing = false
                    }
                }
            })
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

    private fun observeViewModel(){
        viewModel.stories.observe(this, Observer {stories ->
            stories?.let {
                storiesFeeder.visibility = View.VISIBLE
                storiesFeederAdapter.updateStoriesList(stories.toList())
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.actionSettings -> {
                view?.let { Navigation.findNavController(it).navigate(FeederFragmentDirections.actionSettings())}
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
