package cn.krisez.basic

import android.view.View
import cn.krisez.common.CommonBaseActivity

class DexActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("Dex")
        addRightHelp("https://developer.android.google.cn/studio/build/multidex", "Dex")
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_dex, null)
}