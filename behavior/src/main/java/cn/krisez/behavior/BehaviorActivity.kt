package cn.krisez.behavior

import android.content.Intent
import android.view.View
import cn.krisez.behavior.camera.CameraXActivity
import cn.krisez.behavior.media.MediaActivity
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_behavior.*

class BehaviorActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("行为")
        camerax.setOnClickListener { startActivity(Intent(this, CameraXActivity::class.java)) }
        media.setOnClickListener { startActivity(Intent(this, MediaActivity::class.java)) }
        setting.setOnClickListener { startActivity(Intent(this, SettingActivity::class.java)) }
        share.setOnClickListener { startActivity(Intent(this,ShareActivity::class.java)) }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_behavior, null)

}