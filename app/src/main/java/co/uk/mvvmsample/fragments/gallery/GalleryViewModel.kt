package co.uk.mvvmsample.fragments.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text

    var click:MutableLiveData<Boolean>? = null

    init {
        click = MutableLiveData(false)
    }

    fun goNext(){
        click?.value =true

    }
}