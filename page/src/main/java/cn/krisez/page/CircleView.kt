package cn.krisez.page

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView : View {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var drawColor = Color.BLACK
    private var mWidth: Int = 0
    private var mHeight: Int = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
    }

    public fun setDrawColor(color: Int) {
        drawColor = color
        invalidate()
    }

    public fun getDrawColor(): Int = drawColor

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            it.drawCircle(mWidth / 2.toFloat(), mHeight / 2.toFloat(), mWidth / 2.toFloat(), Paint().apply { color = drawColor })
        }
    }
}