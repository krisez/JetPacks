package cn.krisez.basic

import android.content.Intent
import android.view.View
import cn.krisez.basic.ktx.*
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_ktx.*

class KtxActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("KTX")
        addRightHelp("https://developer.android.google.cn/kotlin/first", "KTX")
        kuozhanhanshu.setOnClickListener{
            startActivity(Intent(this, KzhsActivity::class.java))
        }
        kuozhanshuxing.setOnClickListener{
            startActivity(Intent(this, KzsxActivity::class.java))
        }
        lambda.setOnClickListener{
            startActivity(Intent(this, LambdaActivity::class.java))
        }
        canshu.setOnClickListener{
            startActivity(Intent(this, CsActivity::class.java))
        }
        xiecheng.setOnClickListener{
            startActivity(Intent(this, XcActivity::class.java))
        }
        
    }

    override fun view(): View? {
        return View.inflate(this, R.layout.activity_ktx, null)
    }
}