package cn.krisez.basic

import android.view.View
import cn.krisez.common.CommonBaseActivity

class TvActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("")
        addRightHelp("https://developer.android.google.cn/training/tv?hl=zh_cn", "TV")
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_tv, null)

}
