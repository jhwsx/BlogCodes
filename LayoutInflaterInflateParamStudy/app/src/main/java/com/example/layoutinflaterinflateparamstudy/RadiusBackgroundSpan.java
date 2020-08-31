package com.example.layoutinflaterinflateparamstudy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

public class RadiusBackgroundSpan extends ReplacementSpan {
    private int mBgColor;
    private int mRadius;
    private int mTextColor;
    private int mTextSize;
    private int mPaddingHorizontal;
    private String mText;
    private int mMarginLeft;
    private int mMarginRight;

    public RadiusBackgroundSpan(int bgColor, int radius, int textColor, int textSize, int paddingHorizontal, String text){
        mBgColor = bgColor;
        mRadius = radius;
        mTextColor = textColor;
        mTextSize = textSize;
        mPaddingHorizontal = paddingHorizontal;
        mText = text;
        mMarginLeft = UIUtil.dpToPx(4);
        mMarginRight = UIUtil.dpToPx(4);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        paint.setTextSize(mTextSize);
        return (int) paint.measureText(mText) + 2*mPaddingHorizontal + mMarginLeft + mMarginRight;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        final int offsetToTop = UIUtil.dpToPx(2);
        paint.setTextSize(mTextSize);
        paint.setAntiAlias(true);
        RectF rect = new RectF();
        rect.left = (int) x + mMarginLeft;
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int marginVertical = (bottom - top - fontMetrics.descent + fontMetrics.top)/2;
        rect.top = top + marginVertical - offsetToTop; //视觉感觉偏下了，往上一点点
        rect.bottom = bottom - marginVertical;
        rect.right = rect.left + (int) paint.measureText(mText) + 2*mPaddingHorizontal;

        paint.setColor(mBgColor);
        canvas.drawRoundRect(rect, mRadius, mRadius, paint);

        paint.setColor(mTextColor);
        float fontShouldOffsetTop = ((fontMetrics.descent - fontMetrics.ascent)/2+fontMetrics.ascent)/2 - offsetToTop/2;
        canvas.drawText(mText,x+mPaddingHorizontal+mMarginLeft,y + fontShouldOffsetTop,paint);
    }
}