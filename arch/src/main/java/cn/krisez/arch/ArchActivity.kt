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
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_arch, null)
}