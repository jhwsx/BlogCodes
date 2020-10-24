package com.example.listadapterstudy

import android.graphics.Rect
import android.view.View
import kotlin.jvm.JvmOverloads
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.listadapterstudy.GridSpaceDecoration

/**
 * Created by Mr_Zeng
 *
 *
 * 透明间隔的网格Decoration,可以通过构造方法来设置相应位置的间隔
 * [ &lt;p&gt;][.GridSpaceDecoration]
 */
// https://juejin.im/post/5c066ca4e51d451db1421ba0#heading-16
class GridSpaceDecoration
/**
 * @see .GridSpaceDecoration
 */ @JvmOverloads constructor(
    var horizontal: Int,
    var vertical: Int,
    var left: Int = 0,
    var right: Int = 0,
    var top: Int = 0,
    private var mBottom: Int = 0
) : ItemDecoration() {
    private var isFirst = true
    protected var mManager: GridLayoutManager? = null
    protected var mSpanCount = 0
    protected var mChildCount = 0
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        if (isFirst) {
            init(parent)
            isFirst = false
        }
        if (mManager!!.orientation == LinearLayoutManager.VERTICAL) {
            handleVertical(outRect, view, parent, state)
        } else {
            handleHorizontal(outRect, view, parent, state)
        }
    }

    /**
     * 计算Item的左边Decoration(outRect.left)尺寸,当orientation为Vertical时调用
     * @param spanIndex 需要计算的Item对应的spanIndex
     * @param sizeAvg 每个Item需要填充的尺寸
     * @return outRect.left
     */
    private fun computeLeft(spanIndex: Int, sizeAvg: Int): Int {
        return if (spanIndex == 0) {
            left
        } else if (spanIndex >= mSpanCount / 2) {
            //从右边算起
            sizeAvg - computeRight(spanIndex, sizeAvg)
        } else {
            //从左边算起
            horizontal - computeRight(spanIndex - 1, sizeAvg)
        }
    }

    /**
     * 计算Item的右边Decoration(outRect.right)尺寸,当orientation为Vertical时调用
     * @param spanIndex 需要计算的Item对应的spanIndex
     * @param sizeAvg 每个Item需要填充的尺寸
     * @return outRect.right
     */
    private fun computeRight(spanIndex: Int, sizeAvg: Int): Int {
        return if (spanIndex == mSpanCount - 1) {
            right
        } else if (spanIndex >= mSpanCount / 2) {
            //从右边算起
            horizontal - computeLeft(spanIndex + 1, sizeAvg)
        } else {
            //从左边算起
            sizeAvg - computeLeft(spanIndex, sizeAvg)
        }
    }

    /**
     * 计算Item的顶部边Decoration(outRect.Top)尺寸,当orientation为Horizontal时调用
     * @param spanIndex 需要计算的Item对应的spanIndex
     * @param sizeAvg 每个Item需要填充的尺寸
     * @return outRect.top
     */
    private fun computeTop(spanIndex: Int, sizeAvg: Int): Int {
        return if (spanIndex == 0) {
            top
        } else if (spanIndex >= mSpanCount / 2) {
            //从底部算起
            sizeAvg - computeBottom(spanIndex, sizeAvg)
        } else {
            //从顶端算起
            vertical - computeBottom(spanIndex - 1, sizeAvg)
        }
    }

    /**
     * 计算Item的底部Decoration(outRect.bottom)尺寸,当orientation为Horizontal时调用
     * @param spanIndex 需要计算的Item对应的spanIndex
     * @param sizeAvg 每个Item需要填充的尺寸
     * @return outRect.bottom
     */
    private fun computeBottom(spanIndex: Int, sizeAvg: Int): Int {
        return if (spanIndex == mSpanCount - 1) {
            mBottom
        } else if (spanIndex >= mSpanCount / 2) {
            //从底部算起
            vertical - computeTop(spanIndex + 1, sizeAvg)
        } else {
            //从顶端算起
            sizeAvg - computeTop(spanIndex, sizeAvg)
        }
    }

    /**
     * orientation为Vertical时调用，处理Vertical下的Offset
     * [.getItemOffsets]
     */
    private fun handleVertical(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val lp = view.layoutParams as GridLayoutManager.LayoutParams
        val childPos = parent.getChildAdapterPosition(view)
        val sizeAvg = ((horizontal * (mSpanCount - 1) + left + right) * 1f / mSpanCount).toInt()
        val spanSize = lp.spanSize
        val spanIndex = lp.spanIndex
        outRect.left = computeLeft(spanIndex, sizeAvg)
        if (spanSize == 0 || spanSize == mSpanCount) {
            outRect.right = sizeAvg - outRect.left
        } else {
            outRect.right = computeRight(spanIndex + spanSize - 1, sizeAvg)
        }
        outRect.top = vertical / 2
        outRect.bottom = vertical / 2
        if (isFirstRaw(childPos)) {
            outRect.top = top
        }
        if (isLastRaw(childPos)) {
            outRect.bottom = mBottom
        }
    }

    /**
     * orientation为Horizontal时调用，处理Horizontal下的Offset
     * [.getItemOffsets]
     */
    private fun handleHorizontal(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val lp = view.layoutParams as GridLayoutManager.LayoutParams
        val childPos = parent.getChildAdapterPosition(view)
        val spanSize = lp.spanSize
        val spanIndex = lp.spanIndex
        val sizeAvg = ((vertical * (mSpanCount - 1) + top + mBottom) * 1f / mSpanCount).toInt()
        outRect.top = computeTop(spanIndex, sizeAvg)
        if (spanSize == 0 || spanSize == mSpanCount) {
            outRect.bottom = sizeAvg - outRect.top
        } else {
            outRect.bottom = computeBottom(spanIndex + spanSize - 1, sizeAvg)
        }
        outRect.left = horizontal / 2
        outRect.right = horizontal / 2
        if (isFirstRaw(childPos)) {
            outRect.left = left
        }
        if (isLastRaw(childPos)) {
            outRect.right = right
        }
    }

    /**
     * 初始化
     */
    private fun init(parent: RecyclerView) {
        val manager = parent.layoutManager
        require(manager is GridLayoutManager) {
            "LayoutManger must instance of GridLayoutManager " +
                    "while using GridSpaceDecoration"
        }
        mManager = manager
        mSpanCount = spanCount
        mChildCount = parent.adapter!!.itemCount
    }

    protected fun getSpanIndex(pos: Int): Int {
        val spanIndex: Int
        val lookup = mManager!!.spanSizeLookup
        lookup.isSpanIndexCacheEnabled = true
        spanIndex = lookup.getSpanIndex(pos, mSpanCount)
        return spanIndex
    }

    protected val spanCount: Int
        protected get() = mManager!!.spanCount

    protected fun isFirstColumn(params: GridLayoutManager.LayoutParams, pos: Int): Boolean {
        return params.spanIndex == 0
    }

    protected fun isFirstRaw(pos: Int): Boolean {
        if (mChildCount <= 0) {
            return false
        }
        val lookup = mManager!!.spanSizeLookup
        return lookup.getSpanGroupIndex(pos, mSpanCount) == lookup.getSpanGroupIndex(0, mSpanCount)
    }

    protected fun isLastRaw(pos: Int): Boolean {
        if (mChildCount <= 0) {
            return false
        }
        val lookup = mManager!!.spanSizeLookup
        return lookup.getSpanGroupIndex(
            pos,
            mSpanCount
        ) == lookup.getSpanGroupIndex(mChildCount - 1, mSpanCount)
    }

    protected fun isLastColumn(params: GridLayoutManager.LayoutParams, pos: Int): Boolean {
        val index = params.spanIndex
        val size = params.spanSize
        return index + size == mSpanCount
    }

    fun setmTop(mTop: Int) {
        top = mTop
    }

    fun getmBottom(): Int {
        return mBottom
    }

    fun setmBottom(mBottom: Int) {
        this.mBottom = mBottom
    }

    companion object {
        /**
         * 设置各个参数为同一个值的工厂方法.
         * @param dp
         * @return
         */
        fun newInstance(dp: Int): GridSpaceDecoration {
            return GridSpaceDecoration(
                dp.dp2px(),
                dp.dp2px(),
                dp.dp2px(),
                dp.dp2px(),
                dp.dp2px(),
                dp.dp2px()
            )
        }
    }
    /**
     * @param horizontal 内部水平距离(px)
     * @param vertical   内部竖直距离(px)
     * @param left       最左边距离(px)，默认为0
     * @param right      最右边距离(px),默认为0
     * @param top        最顶端距离(px),默认为0
     * @param mBottom     最底端距离(px),默认为0
     */
    /**
     * @see .GridSpaceDecoration
     */
}