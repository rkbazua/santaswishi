package com.rbazua.wishipets.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rbazua.wishipets.model.Story

class StoryDetailViewModel : ViewModel() {
    val storyLiveData = MutableLiveData<Story>()

    fun fetch(){
        val story1 = Story("1", "Ayúdenme a encontrar a mi perrito", "Hola, mi perrito se escapó de la casa el 26 de septiembre del 2012")
        storyLiveData.value = story1
    }
}