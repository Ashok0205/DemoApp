package co.uk.mvvmsample.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import co.uk.mvvmsample.model.HomeData



class HomeViewModel : ViewModel() {

    private var homeRepository: HomeRepository? = null
   lateinit var progressBar:MutableLiveData<Boolean>

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var click:MutableLiveData<Boolean>? = null


    init {
        homeRepository = HomeRepository()
        progressBar = MutableLiveData<Boolean>(false)
        click = MutableLiveData(false)
    }

    fun getAllEmployee(): MutableLiveData<List<HomeData>>? {
        return homeRepository?.getMutableLiveData()
    }

    fun goNext(){
        click?.value =true
    }

}