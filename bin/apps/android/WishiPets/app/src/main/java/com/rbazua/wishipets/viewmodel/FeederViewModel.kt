package com.rbazua.wishipets.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rbazua.wishipets.model.StoriesApiService
import com.rbazua.wishipets.model.Story
import com.rbazua.wishipets.model.StoryDatabase
import com.rbazua.wishipets.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeederViewModel(application: Application): BaseViewModel(application) {
    private var prefsHelper = SharedPreferencesHelper(getApplication())
    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    private val storiesService = StoriesApiService()
    private val disposable = CompositeDisposable()

    val stories = MutableLiveData<List<Story>>()
    val storiesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val updateTime = prefsHelper.getUpdateTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            fetchFromDatabase()
        } else{
            fetchFromRemote()
        }
    }

    fun refreshBypassCache()
    {
        fetchFromRemote()
    }
    private fun fetchFromDatabase() {
        //loading.value = true
        launch {
            val stories = StoryDatabase(getApplication()).storyDao().getAllStories()
            storiesRetrieved(stories)
            Toast.makeText(getApplication(), "Stories retrieved from database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchFromRemote() {
        //loading.value = true
        disposable.add(
            storiesService.getStories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Story>>(){

                    override fun onSuccess(storiesList: List<Story>) {
                        storeStoriesLocally(storiesList)
                        Toast.makeText(getApplication(), "Stories retrieved from endpoint", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        storiesLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun storiesRetrieved(storiesList: List<Story>){
        stories.value = storiesList
        storiesLoadError.value = false
        loading.value = false
    }

    private fun storeStoriesLocally(list: List<Story>) {
        launch {
            val dao = StoryDatabase(getApplication()).storyDao()
            dao.deleteAllStories()
            val result: List<Long> = dao.insertAll(*list.toTypedArray())
            var i = 0
            while(i < list.size){
                list[i].uuid = result[i]
                ++i
            }
            storiesRetrieved(list)
        }
        prefsHelper.saveUpdateTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}