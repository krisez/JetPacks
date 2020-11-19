package cn.krisez.arch

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_live_data.*
import kotlinx.coroutines.launch

class LiveDataActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("LiveData")
        addRightHelp(
            "https://developer.android.google.cn/topic/libraries/architecture/livedata",
            ""
        )
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.getContent()?.observe(this,
            { t -> live_data.text = t })

        test_btn.setOnClickListener{
            model.setNewContent("test new content")
        }

        lifecycleScope.launch {  }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_live_data, null)
}