package cn.krisez.basic

import android.view.View
import cn.krisez.common.CommonBaseActivity

class WearActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("Wear")
        addRightHelp("https://developer.android.google.cn/training/wearables?hl=zh_cn", "Wear")
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_wear, null)
}
