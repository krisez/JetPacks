package cn.krisez.basic.ktx

import android.view.View
import cn.krisez.basic.R
import cn.krisez.common.CommonBaseActivity

//参数设置
class CsActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("命名参数")
        addRightHelp("http://www.kotlincn.net/docs/reference/functions.html","函数参数")
    }

    override fun view(): View? {
        return View.inflate(this, R.layout.activity_cs, null)
    }

}
