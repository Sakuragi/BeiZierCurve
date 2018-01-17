package com.jim.beiziercurve.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

/**
 * Created by Jim on 2018/1/17 0017.
 */
class WaveView : View {

    private var mPaint: Paint by Delegates.notNull<Paint>()
    private var mCanvas: Canvas by Delegates.notNull<Canvas>()
    private var wavePath: Path by Delegates.notNull<Path>()
    private var startXPoint: Float by Delegates.notNull<Float>()
    private var startYPoint: Float by Delegates.notNull<Float>()

    init {
        mPaint = Paint()
        mCanvas = Canvas()
        wavePath = Path()
        mPaint.strokeWidth=10f
        mPaint.color=Color.BLACK
        mPaint.style=Paint.Style.STROKE
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        startXPoint=(width/4).toFloat()
        startYPoint=(height/2).toFloat()
        wavePath.reset()
        wavePath.moveTo(startXPoint,startYPoint)
        wavePath.quadTo(startXPoint+80,startYPoint+160,startXPoint+160,startYPoint)
        canvas?.drawPath(wavePath,mPaint)
        wavePath.moveTo(startXPoint+160,startYPoint)
        wavePath.quadTo(startXPoint+240,startYPoint-160,startXPoint+320,startYPoint)
        canvas?.drawPath(wavePath,mPaint)

    }

}