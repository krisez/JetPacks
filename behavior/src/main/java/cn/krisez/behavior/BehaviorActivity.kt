package cn.krisez.behavior

import android.content.Intent
import android.view.View
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_behavior.*

class BehaviorActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("行为")
        camerax.setOnClickListener { startActivity(Intent(this, CameraXActivity::class.java)) }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_behavior, null)

}