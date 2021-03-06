package cn.krisez.arch

import android.content.Intent
import android.view.View
import cn.krisez.common.CommonBaseActivity
import cn.krisez.common.WebActivity
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import kotlinx.android.synthetic.main.activity_arch.*

class ViewModelActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("ViewModel")
        addRightHelp("https://developer.android.google.cn/topic/libraries/architecture/viewmodel", "")
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_view_model, null)
}