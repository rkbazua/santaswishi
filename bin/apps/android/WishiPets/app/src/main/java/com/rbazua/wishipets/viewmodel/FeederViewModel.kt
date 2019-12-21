package com.rbazua.wishipets.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rbazua.wishipets.model.Story

class FeederViewModel: ViewModel() {
    val stories = MutableLiveData<List<Story>>()
    val storiesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val story1 = Story("1", "Ayúdenme a encontrar a mi perrito", "Hola, mi perrito se escapó de la casa el 26 de septiembre del 2012")
        val story2 = Story("2", "Me llamo Pelillos, ¿me adoptas?", "Soy un perrito de mediana edad, muy cariñoso y juguetón")

        val storiesList: ArrayList<Story> = arrayListOf<Story>(story1, story2)

        stories.value = storiesList
        storiesLoadError.value = false
        loading.value = false
    }
}