package com.hjq.demo.other

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LineItemDecoration(context: Context, orientation: Int) :
    RecyclerView.ItemDecoration() {

    private val mOrientation: Int
    private val mContext: Context
    private val attrs = intArrayOf(android.R.attr.listDivider)
    private var mDividerHeight = 0
    private var mPaint: Paint? = null
    private var mDivider: Drawable?

    /**
     * 默认分割线：高度为2px，颜色为灰色
     *
     * @param context
     * @param orientation 列表方向
     */
    init {
        require(!(orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL)) { "请输入正确参数" }
        mContext = context
        mOrientation = orientation
        val a = context.obtainStyledAttributes(attrs)
        mDivider = a.getDrawable(0)
        a.recycle()
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation 列表方向
     * @param drawableId  分割线图片
     */
    constructor(context: Context, orientation: Int, drawableId: Int) : this(context, orientation) {
        mDivider = ContextCompat.getDrawable(context, drawableId)
        mDividerHeight = mDivider!!.intrinsicHeight
    }

    /**
     *
     * @param context
     * @param orientation 列表方向
     * @param mDividerHeight 分割线高度
     * @param mDividerColor 分割线颜色
     */
    constructor(context: Context, orientation: Int, mDividerHeight: Int, mDividerColor: Int) : this(
        context,
        orientation
    ) {
        this.mDividerHeight = mDividerHeight
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.color = mDividerColor
        mPaint!!.style = Paint.Style.FILL
    }

    //绘制分割线
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    //绘制横向 item 分割线
    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.measuredWidth - parent.paddingRight
        val childSize = parent.childCount - 1
        if (childSize >= 1) {
            for (i in 0 until childSize) {
                val child = parent.getChildAt(i)
                val layoutParams = child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + layoutParams.bottomMargin
                val bottom = top + mDividerHeight
                if (mDivider != null) {
                    mDivider!!.setBounds(left, top, right, bottom)
                    mDivider!!.draw(canvas)
                }
                if (mPaint != null) {
                    canvas.drawRect(
                        left.toFloat(),
                        top.toFloat(),
                        right.toFloat(),
                        bottom.toFloat(),
                        mPaint!!
                    )
                }
            }
        }
    }

    //绘制纵向 item 分割线
    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.measuredHeight - parent.paddingBottom
        val childSize = parent.childCount - 1
        if (childSize >= 1) {
            for (i in 0 until childSize) {
                val child = parent.getChildAt(i)
                val layoutParams = child.layoutParams as RecyclerView.LayoutParams
                val left = child.right + layoutParams.rightMargin
                val right = left + mDividerHeight
                if (mDivider != null) {
                    mDivider!!.setBounds(left, top, right, bottom)
                    mDivider!!.draw(canvas)
                }
                if (mPaint != null) {
                    canvas.drawRect(
                        left.toFloat(),
                        top.toFloat(),
                        right.toFloat(),
                        bottom.toFloat(),
                        mPaint!!
                    )
                }
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect[0, 0, 0] = mDividerHeight
    }
}