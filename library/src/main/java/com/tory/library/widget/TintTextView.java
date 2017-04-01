package com.tory.library.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.tory.library.R;

/**
 * @Author: tory
 * Create: 2017/3/19
 * Update: 2017/3/19
 */
public class TintTextView extends AppCompatTextView {

    ColorStateList mColorTint;

    public TintTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public TintTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public TintTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr){
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.TintViewDrawable, defStyleAttr, 0);
        int color = a.getColor(R.styleable.TintViewDrawable_drawableTint, Color.TRANSPARENT);
        mColorTint = ColorStateList.valueOf(color);
        a.recycle();
        tintCompoundDrawables();
    }

    public void setDrawableColorTint(int color){
        mColorTint = ColorStateList.valueOf(color);
        tintCompoundDrawables();
    }

    private void tintCompoundDrawables() {
        Drawable[] drawables = getCompoundDrawablesRelative();
        if(drawables == null ) return;
        for (int i = 0; i < drawables.length; i++) {
            Drawable icon = drawables[i];
            if (icon != null) {
                Drawable.ConstantState state = icon.getConstantState();
                icon = DrawableCompat.wrap(state == null ? icon : state.newDrawable()).mutate();
                icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                DrawableCompat.setTintList(icon, mColorTint);
                drawables[i] = icon;
            }
        }
        TextViewCompat.setCompoundDrawablesRelative(this,
                drawables[0],drawables[1],drawables[2],drawables[3]);
    }
}