package cn.krisez.page

import android.view.View
import cn.krisez.common.CommonBaseActivity

class PageActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("界面")
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_page, null)

}