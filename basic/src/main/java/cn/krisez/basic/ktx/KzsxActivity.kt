package cn.krisez.basic.ktx

import android.view.View
import cn.krisez.basic.R
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_kz.*

class KzsxActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("扩展属性")
        addRightHelp("http://www.kotlincn.net/docs/reference/extensions.html","扩展")
        example_code.text = "val String.empty: Boolean\n" +
                "    get() = TextUtils.isEmpty(this)"
        example_describe.text = "如此对每一个String对象直接调用empty即可得到是否为空，无需再判断null\naaa is \"aaa\".empty -> ${"aaa".empty}\nkotlin官方已加入对应的方法"
    }

    override fun view(): View? {
        return View.inflate(this, R.layout.activity_kz, null)
    }

}
