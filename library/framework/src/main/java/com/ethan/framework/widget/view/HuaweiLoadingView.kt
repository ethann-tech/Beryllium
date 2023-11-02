package com.ethan.beryllium.common.widget.view

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import com.ethan.framework.R


class HuaweiLoadingView : View {

    private lateinit var mContext: Context

    /** 默认的loading宽高 */
    private val VIEW_DEFAULT_SIZE = 50

    /** 每个小圆的角度间距 */
    private val DEGREE_CIRCLE = 360 / CIRCLE_TOTAL_COUNT

    private var mCircleList = mutableListOf<CircleInfo>()

    /** 最大的小圆的半径 */
    private var mMaxCircleRadius = 0f

    /** 整个loadingview的半径 */
    private var mRadius = 0
    private var mColor: Int = Color.parseColor("#FF00FF")
    private var mDuration: Int = 2000
    private var mAnimator: ValueAnimator = ValueAnimator.ofInt(0, CIRCLE_TOTAL_COUNT - 1)
    private var mAnimateValue: Int = 0

    private lateinit var mPaint: Paint


    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    /**
     * //顺时针开始：1-7大小相同，颜色也相同，8,9,10,11大小依次变大，9和11大小颜色一样
     */
    private fun buildCircleInfo() {
        val minCircleRadius = mRadius / 30f
        mMaxCircleRadius = 2 * minCircleRadius
        for (i in 0 until CIRCLE_TOTAL_COUNT) {
            Log.d("hwloading", "i = $i")
            when (i) {
                7     -> mCircleList.add(CircleInfo(minCircleRadius * 1.25f, (255 * 0.6f).toInt()))
                8     -> mCircleList.add(CircleInfo(minCircleRadius * 1.5f, (255 * 0.7f).toInt()))
                9, 11 -> mCircleList.add(CircleInfo(minCircleRadius * 1.75f, (255 * 0.9f).toInt()))
                10    -> mCircleList.add(CircleInfo(minCircleRadius * 2f, 255))
                else  -> mCircleList.add(CircleInfo(minCircleRadius, (255 * 0.5f).toInt()))
            }
        }
    }

    private fun init(context: Context, attr: AttributeSet?) {
        mContext = context
        setAttrStyle(attr)
        mPaint = Paint(Color.GRAY)
        mPaint.color = mColor
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL
    }

    private fun setAttrStyle(attr: AttributeSet?) {
        val ta: TypedArray = mContext.obtainStyledAttributes(attr, R.styleable.HuaweiLoadingView)
        mColor = ta.getColor(R.styleable.HuaweiLoadingView_hw_color, Color.parseColor("#FF00FF"))
        mDuration = ta.getInt(R.styleable.HuaweiLoadingView_hw_duration, 1000)
        ta.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureHeight = MeasureSpec.getSize(heightMeasureSpec)
        val measureWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val measureHeightMode = MeasureSpec.getMode(heightMeasureSpec)

        val width: Int = if (measureWidthMode == MeasureSpec.EXACTLY) measureWidth else VIEW_DEFAULT_SIZE
        val height: Int = if (measureHeightMode == MeasureSpec.EXACTLY) measureHeight else VIEW_DEFAULT_SIZE
        mRadius = if (width > height) height else width
        setMeasuredDimension(mRadius, mRadius)

        buildCircleInfo()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.rotate(DEGREE_CIRCLE * mAnimateValue.toFloat(), mRadius / 2f, mRadius / 2f)
        var circleInfo: CircleInfo
        for (i in 0 until CIRCLE_TOTAL_COUNT) {
            circleInfo = mCircleList[i]
            mPaint.alpha = circleInfo.color
            canvas.drawCircle(mRadius / 2f, mMaxCircleRadius, circleInfo.radius, mPaint)
            canvas.rotate(DEGREE_CIRCLE.toFloat(), mRadius / 2f, mRadius / 2f)
        }
    }


    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == VISIBLE) {
            startAnimation()
        } else {
            stopAnimation()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopAnimation()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        stopAnimation()
    }

    private fun startAnimation() {
        if (mAnimator.isStarted) {
            return
        }
        mAnimator.duration = mDuration.toLong()
        mAnimator.repeatCount = ValueAnimator.INFINITE
        mAnimator.repeatMode = ValueAnimator.RESTART
        mAnimator.addUpdateListener(updateListener)
        mAnimator.interpolator = LinearInterpolator()
        mAnimator.start()
    }

    private fun stopAnimation() {
        mAnimator.removeUpdateListener(updateListener)
        mAnimator.removeAllUpdateListeners()
        mAnimator.cancel()
    }

    private var updateListener = ValueAnimator.AnimatorUpdateListener {
        run {
            this.mAnimateValue = it.animatedValue as Int
            invalidate()
        }
    }

    class CircleInfo(radius: Float, color: Int) {
        var radius: Float = 0f
        var color: Int = 0

        init {
            this.radius = radius
            this.color = color
        }
    }


    companion object {
        /* 小圆的个数 */
        private const val CIRCLE_TOTAL_COUNT = 12
    }
}