package cn.krisez.page

import android.view.View
import cn.krisez.common.CommonBaseActivity

class LayoutActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("Layout")
        addRightHelp("https://developer.android.google.cn/guide/topics/ui/declaring-layout", "布局")
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_layout, null)

}
