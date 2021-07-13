package com.udacity.shoestore.ui

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Rect
import android.util.AttributeSet
import com.udacity.shoestore.R
import kotlin.math.roundToInt


class TextViewDrawableSize : androidx.appcompat.widget.AppCompatTextView {
    private var mDrawableWidth = 0
    private var mDrawableHeight = 0

    constructor(context: Context) : super(context) {
        init(context, null, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs, defStyleAttr, 0)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val array: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.TextViewDrawableSize,
            defStyleAttr,
            defStyleRes
        )
        try {
            mDrawableWidth = array.getDimensionPixelSize(
                R.styleable.TextViewDrawableSize_compoundDrawableWidth,
                -1
            )
            mDrawableHeight = array.getDimensionPixelSize(
                R.styleable.TextViewDrawableSize_compoundDrawableHeight,
                -1
            )
        } finally {
            array.recycle()
        }
        if (mDrawableWidth > 0 || mDrawableHeight > 0) {
            initCompoundDrawableSize()
        }
    }

    private fun initCompoundDrawableSize() {
        val drawables = compoundDrawables
        for (drawable in drawables) {
            if (drawable == null) {
                continue
            }
            val realBounds: Rect = drawable.bounds
            val scaleFactor: Int = realBounds.height() / realBounds.width()
            var drawableWidth: Int = realBounds.width()
            var drawableHeight: Int = realBounds.height()
            if (mDrawableWidth > 0) {
                // save scale factor of image
                if (drawableWidth > mDrawableWidth) {
                    drawableWidth = mDrawableWidth
                    drawableHeight = drawableWidth * scaleFactor
                }
            }
            if (mDrawableHeight > 0) {
                // save scale factor of image
                if (drawableHeight > mDrawableHeight) {
                    drawableHeight = mDrawableHeight
                    drawableWidth = drawableHeight / scaleFactor
                }
            }
            realBounds.right = realBounds.left + drawableWidth.toDouble().roundToInt()
            realBounds.bottom = realBounds.top + drawableHeight.toDouble().roundToInt()
            drawable.bounds = realBounds
        }
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3])
    }
}