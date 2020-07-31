package cn.krisez.basic

import android.view.View
import cn.krisez.common.CommonBaseActivity

class AutoActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("Auto")
        addRightHelp("https://developer.android.google.cn/auto","Auto")
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_auto, null)
}