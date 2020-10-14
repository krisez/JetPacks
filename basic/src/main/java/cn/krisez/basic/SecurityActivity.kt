package cn.krisez.basic

import android.view.View
import cn.krisez.common.CommonBaseActivity

class SecurityActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("Security")
        addRightHelp("https://developer.android.google.cn/topic/security/data?hl=zh_cn", "Security")
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_security, null)

}