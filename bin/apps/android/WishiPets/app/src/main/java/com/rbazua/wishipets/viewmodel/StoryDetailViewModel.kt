package com.rbazua.wishipets.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rbazua.wishipets.model.Story
import com.rbazua.wishipets.model.StoryDatabase
import kotlinx.coroutines.launch

class StoryDetailViewModel(application: Application) : BaseViewModel(application) {
    val storyLiveData = MutableLiveData<Story>()

    fun fetch(petuid: String){
        launch {
            val story = StoryDatabase(getApplication()).storyDao().getStory(petuid)
            storyLiveData.value = story
        }

    }
}