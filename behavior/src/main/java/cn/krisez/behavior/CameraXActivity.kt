package cn.krisez.behavior

import android.content.Intent
import android.view.View
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_camera_x.*

class CameraXActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("CameraX")
        addRightHelp("https://developer.android.google.cn/training/camerax", "CameraX")
        camera_x_preview.setOnClickListener { startActivity(Intent(this, CameraPreActivity::class.java)) }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_camera_x, null)

}