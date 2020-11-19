package cn.krisez.arch

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private var content = MutableLiveData<String>()

    private val data:LiveData<String> = liveData {
        emit("asd")
    }

    init {
        viewModelScope.launch {
            createContent()
        }
    }

    fun getContent(): LiveData<String>? {
        return content
    }

    fun setNewContent(content: String) {
        this.content.value = content
    }

    private fun createContent() {
        content.value = "this is content"
    }
}
