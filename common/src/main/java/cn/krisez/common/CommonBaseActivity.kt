package cn.krisez.common

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_common_base.*

abstract class CommonBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_base)
        QMUIStatusBarHelper.translucent(this)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
        view()?.let{
            container.addView(it, FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT))
        }
        init()
    }

    protected fun setTitle(title: String) {
        topbar.addLeftBackImageButton().setOnClickListener { finish() }
        topbar.setTitle(title)
    }

    abstract fun init()
    abstract fun view(): View?
}