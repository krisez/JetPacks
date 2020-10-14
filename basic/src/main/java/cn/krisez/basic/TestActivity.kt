package cn.krisez.basic

import android.view.View
import cn.krisez.common.CommonBaseActivity

class TestActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("测试")
        addRightHelp("https://developer.android.google.cn/training/testing?hl=zh_cn", "测试")
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_test, null)

}
