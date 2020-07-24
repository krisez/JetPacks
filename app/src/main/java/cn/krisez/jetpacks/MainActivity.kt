package cn.krisez.jetpacks

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import androidx.appcompat.app.AppCompatActivity
import cn.krisez.arch.ArchActivity
import cn.krisez.basic.BasicActivity
import cn.krisez.behavior.BehaviorActivity
import cn.krisez.page.PageActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initColor()
        basic.setOnClickListener { startActivity(Intent(this, BasicActivity::class.java)) }
        arch.setOnClickListener { startActivity(Intent(this, ArchActivity::class.java)) }
        behavior.setOnClickListener { startActivity(Intent(this, BehaviorActivity::class.java)) }
        page.setOnClickListener { startActivity(Intent(this, PageActivity::class.java)) }
    }

    private fun initColor() {
        basic.setBackgroundColor(Color.parseColor(randColor()))
        arch.setBackgroundColor(Color.parseColor(randColor()))
        behavior.setBackgroundColor(Color.parseColor(randColor()))
        page.setBackgroundColor(Color.parseColor(randColor()))
    }

    private fun randColor(): String {
        var r = Integer.toHexString(Random.nextInt(256)).toUpperCase(Locale.getDefault())
        var g = Integer.toHexString(Random.nextInt(256)).toUpperCase(Locale.getDefault())
        var b = Integer.toHexString(Random.nextInt(256)).toUpperCase(Locale.getDefault())
        r = if (r.length == 1) "0$r" else r
        g = if (g.length == 1) "0$g" else g
        b = if (b.length == 1) "0$b" else b
        return "#$r$g$b"
    }

    private var lastY = 0f
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (it.action) {
                ACTION_DOWN -> {
                    lastY = event.y
                    return true
                }
                ACTION_UP -> {
                    if (lastY != event.y) {
                        initColor()
                    }
                    return true
                }
                else -> {

                }
            }
        }
        return super.onTouchEvent(event)
    }
}