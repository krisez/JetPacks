package cn.krisez.arch

import android.content.Intent
import android.view.View
import cn.krisez.common.CommonBaseActivity
import cn.krisez.common.WebActivity
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import kotlinx.android.synthetic.main.activity_arch.*

class ArchActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("架构")
        addRightHelp("https://developer.android.google.cn/jetpack/guide", "架构指南")
        doc_arch.setOnClickListener {
            startActivity(Intent(this, WebActivity::class.java).putExtra("url", "https://developer.android.google.cn/topic/libraries/architecture/"))
        }
        doc_lifecycle.setOnClickListener { startActivity(Intent(this, LifeCycleActivity::class.java)) }
        doc_live_data.setOnClickListener { startActivity(Intent(this, LiveDataActivity::class.java)) }
        doc_view_model.setOnClickListener { startActivity(Intent(this, ViewModelActivity::class.java)) }
        doc_room.setOnClickListener { startActivity(Intent(this, RoomActivity::class.java)) }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_arch, null)
}