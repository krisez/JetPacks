package cn.krisez.page

import android.animation.*
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.animation.BounceInterpolator
import androidx.core.animation.addListener
import cn.krisez.common.CommonBaseActivity
import com.qmuiteam.qmui.util.QMUIColorHelper
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import kotlinx.android.synthetic.main.activity_property.*
import kotlin.math.*
import kotlin.random.Random

/**
 * 博客地址：https://blog.csdn.net/qq_34206863/article/details/81236072
 */
class PropertyActivity : CommonBaseActivity() {
    override fun init() {
        setTitle("属性动画")
        btn_linear_animation.setOnClickListener {
            ValueAnimator.ofInt(0, Random.nextInt(300, QMUIDisplayHelper.getScreenWidth(this) - 100))
                    .apply {
                        duration = 2000
                        addUpdateListener {
                            btn_linear_animation.translationX = it.animatedValue.toString()
                                    .toFloat()
                        }
                        start()
                    }
        }
        btn_not_linear_animation.setOnClickListener {
            ValueAnimator.ofInt(0, Random.nextInt(300, QMUIDisplayHelper.getScreenWidth(this) - 100))
                    .apply {
                        duration = 2000
                        addUpdateListener {
                            btn_not_linear_animation.translationX = it.animatedValue.toString()
                                    .toFloat()
                        }
                        start()
                    }
        }
        circle_2.setDrawColor(Color.RED)
        circle_3.setDrawColor(Color.GREEN)
        circle_4.setDrawColor(Color.BLUE)
        circle_5.setDrawColor(Color.YELLOW)

        btn_set_animotion.setOnClickListener {
            val animator1 = colorAnimator(circle_1, Color.BLACK, Color.RED)
            val animator2 = colorAnimator(circle_2, Color.RED, Color.GREEN)
            val animator3 = colorAnimator(circle_3, Color.GREEN, Color.BLUE)
            val animator4 = colorAnimator(circle_4, Color.BLUE, Color.YELLOW)
            val animator5 = colorAnimator(circle_5, Color.YELLOW, Color.BLACK)

            val act1 = actionAnimator(circle_1)
            val act2 = actionAnimator(circle_2)
            val act3 = actionAnimator(circle_3)
            val act4 = actionAnimator(circle_4)
            val act5 = actionAnimator(circle_5)
            AnimatorSet().apply {
                play(animator1).with(act1).before(act2)
                play(animator2).with(act2).before(act3)
                play(animator3).with(act3).before(act4)
                play(animator4).with(act4).before(act5)
                play(animator5).with(act5)
                start()
            }
        }
        btn_reset.setOnClickListener {
            circle_1.setDrawColor(Color.BLACK)
            circle_2.setDrawColor(Color.RED)
            circle_3.setDrawColor(Color.GREEN)
            circle_4.setDrawColor(Color.BLUE)
            circle_5.setDrawColor(Color.YELLOW)
            circle_1.apply {
                translationX = 0f
                translationY = 0f
            }
            circle_2.apply {
                translationX = 0f
                translationY = 0f
            }
            circle_3.apply {
                translationX = 0f
                translationY = 0f
            }
            circle_4.apply {
                translationX = 0f
                translationY = 0f
            }
            circle_5.apply {
                translationX = 0f
                translationY = 0f
            }
        }
    }

    private fun colorAnimator(view: CircleView, startColor: Int, endColor: Int): ValueAnimator {
        return ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 2000
            addUpdateListener {
                view.setDrawColor(QMUIColorHelper.computeColor(startColor, endColor, it.animatedFraction))
            }
        }
    }

    private fun actionAnimator(view: CircleView): AnimatorSet {
        val size = QMUIDisplayHelper.getRealScreenSize(this)
        val screenWidth = size[0]
        val screenHeight = size[1]
        val initY = view.y
        //第一步，挪动到屏幕中间
        //ax2+bx+c=y -> 初始值为函数顶点计算  y=a(x-h)2+k
        //y=ax2
        val a = (screenHeight / 2 - initY) / (screenWidth / 2f).pow(2)
        val ani1 = ValueAnimator.ofFloat(0f, screenWidth / 2 - view.width / 2f).apply {
            duration = 2000
            addUpdateListener {
                view.translationX = it.animatedValue as Float
                view.translationY = a * (it.animatedValue as Float).pow(2)
            }
        }
        //第二部，中间转圈，半径增大
        //a,b为屏幕中心
        //（x-a)+(y-b)2 = r2
        val initX2 = screenWidth / 2 - view.width / 2f
        val initY2 = a * initX2.pow(2)
        val ani2 = ValueAnimator.ofInt(0, Random.nextInt(75, 150)).apply {
            duration = 3000
            interpolator = BounceInterpolator()
            addUpdateListener {
                //代表0-5s
                val time = it.animatedFraction * 5
                when {
                    time < 1 -> {
                        view.translationY = (initY2 + sin(time * PI) * 50).toFloat()
                    }
                    time < 2 -> {
                        view.translationY = (initY2 + sin(time * PI) * 100).toFloat()
                    }
                    time < 3 -> {
                        view.translationY = (initY2 + sin(time * PI) * 170).toFloat()
                    }
                    time < 4 -> {
                        view.translationY = (initY2 + sin(time * PI) * 280).toFloat()
                    }
                    time < 5 -> {
                        view.translationY = (initY2 + sin(time * PI) * 350).toFloat()
                    }
                }
                when {
                    time < 0.5 -> {

                    }
                    time < 1.5 -> {
                        view.translationX = (initX2 + cos(time * PI) * 50).toFloat()
                    }
                    time < 2.5 -> {
                        view.translationX = (initX2 + cos(time * PI) * 90).toFloat()
                    }
                    time < 3.5 -> {
                        view.translationX = (initX2 + cos(time * PI) * 150).toFloat()
                    }
                    time < 4.5 -> {
                        view.translationX = (initX2 + cos(time * PI) * 220).toFloat()
                    }
                    time < 5 -> {
                        view.translationX = (initX2 + cos(time * PI) * 250).toFloat()
                    }
                }
            }
        }
        //第三步，放置屏幕底部
        //从当前位置，二次函数放入
        //ax2+bx+c=y -> 初始值为函数顶点计算  y=a(x-h)2+k
        //y=ax2
        return AnimatorSet().apply {
            play(ani1).before(ani2)
        }
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_property, null)

}