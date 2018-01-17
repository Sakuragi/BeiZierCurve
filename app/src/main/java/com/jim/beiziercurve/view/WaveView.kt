package com.jim.beiziercurve.view

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import java.util.*
import kotlin.properties.Delegates
import android.R.attr.animation


/**
 * Created by Jim on 2018/1/17 0017.
 */
class WaveView : View {

    private var mPaint: Paint by Delegates.notNull<Paint>()
    private var mCanvas: Canvas by Delegates.notNull<Canvas>()
    private var wavePath: Path by Delegates.notNull<Path>()
    private var startXPoint: Float by Delegates.notNull<Float>()
    private var startYPoint: Float by Delegates.notNull<Float>()
    private var endXPoint: Float by Delegates.notNull<Float>()
    private var endYPoint: Float by Delegates.notNull<Float>()
    private val GAPY: Float = 160f
    private var GAPX: Float by Delegates.notNull<Float>()
    private var isAnimationStart:Boolean=false

    init {
        startXPoint = 0f
        mPaint = Paint()
        mCanvas = Canvas()
        wavePath = Path()
        mPaint.strokeWidth = 10f
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        endXPoint = (width).toFloat()
        startYPoint = (height / 2).toFloat()
        GAPX = endXPoint / 4
        wavePath.reset()
        wavePath.moveTo(startXPoint, startYPoint)
        wavePath.quadTo(startXPoint + GAPX, startYPoint + GAPY, startXPoint + GAPX * 2, startYPoint)
        canvas?.drawPath(wavePath, mPaint)
        wavePath.moveTo(startXPoint + GAPX * 2, startYPoint)
        wavePath.quadTo(startXPoint + GAPX * 3, startYPoint - GAPY, startXPoint + GAPX * 4, startYPoint)
        canvas?.drawPath(wavePath, mPaint)

        wavePath.moveTo(startXPoint + GAPX * 4, startYPoint)
        wavePath.quadTo(startXPoint + GAPX * 5, startYPoint + GAPY, startXPoint + GAPX * 6, startYPoint)
        canvas?.drawPath(wavePath, mPaint)

        wavePath.moveTo(startXPoint + GAPX * 6, startYPoint)
        wavePath.quadTo(startXPoint + GAPX * 7, startYPoint - GAPY, startXPoint + GAPX * 8, startYPoint)
        canvas?.drawPath(wavePath, mPaint)

        Log.d("TAG", "endX: " + endXPoint)
        if (!isAnimationStart) {
            startWave()
        }
    }
    var animator:ValueAnimator?=null
    fun startWave() {
        isAnimationStart=true
        animator = ValueAnimator.ofInt(0, -endXPoint.toInt())
        animator?.duration = 2000
        animator?.setInterpolator(LinearInterpolator())
        animator?.repeatCount=ValueAnimator.INFINITE
        animator?.addUpdateListener(ValueAnimator.AnimatorUpdateListener {
            arg1 ->
            Log.d("TAG", "value: " + arg1.getAnimatedValue())
            startXPoint = (arg1.getAnimatedValue() as Int).toFloat()
            if (Math.abs(startXPoint) == endXPoint) {
//                startXPoint=0f
            }
            invalidate()
        })
        animator?.start()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator?.cancel()
    }

}