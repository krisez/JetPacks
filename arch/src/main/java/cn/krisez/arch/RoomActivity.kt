package cn.krisez.arch

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.*
import androidx.room.Room
import cn.krisez.common.CommonBaseActivity
import cn.krisez.common.MyApp
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("Room")
        addRightHelp("https://developer.android.google.cn/topic/libraries/architecture/room", "")
        val viewModel = ViewModelProvider(this).get(RoomModel::class.java)
        viewModel.getContent().observe(this, Observer { t -> text_view.append(t) })

        test_btn.setOnClickListener {
            viewModel.addUserContent(User(0, "hhh", "asd"))
        }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_room, null)

    internal class RoomModel() : ViewModel() {
        private val content: MutableLiveData<String> = MutableLiveData<String>()
        private val db by lazy {
            Room.databaseBuilder(MyApp.sContext, AppDatabase::class.java, "jetpack").build()
        }

        fun getContent(): MutableLiveData<String> {
            return content
        }

        fun addUserContent(user: User) {
            viewModelScope.launch {
                content.value = safeAddUser(user)
            }
        }

        private suspend fun safeAddUser(user: User): String {
            return withContext(Dispatchers.IO) {
                db.userDao().insertAll(user)
                Gson().toJson(user)
            }
        }
    }
}