package cn.krisez.basic.ktx

import android.view.View
import cn.krisez.basic.R
import cn.krisez.common.CommonBaseActivity

class LambdaActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("Lambda")
        addRightHelp("http://www.kotlincn.net/docs/reference/lambdas.html","Lambda")
    }

    override fun view(): View? {
        return View.inflate(this, R.layout.activity_lambda, null)
    }

}
