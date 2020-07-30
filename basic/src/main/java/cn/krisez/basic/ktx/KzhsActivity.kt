package cn.krisez.basic.ktx

import android.view.View
import cn.krisez.basic.R
import cn.krisez.common.CommonBaseActivity

class KzhsActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("扩展函数")
        addRightHelp("http://www.kotlincn.net/docs/reference/extensions.html","扩展")
        toast("toast的扩展")
    }

    override fun view(): View? {
        return View.inflate(this, R.layout.activity_kz, null)
    }

}
