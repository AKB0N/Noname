/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tory.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.tory.library.R;
import com.tory.library.utils.ViewUtil;

/** TextView which resizes dynamically with respect to text length. */
public class ResizingTextTextView extends AppCompatTextView {

  private final int originalTextSize;
  private final int minTextSize;

  public ResizingTextTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    originalTextSize = (int) getTextSize();
    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ResizingText);
    minTextSize =
        (int) a.getDimension(R.styleable.ResizingText_resizing_text_min_size, originalTextSize);
    a.recycle();
  }

  @Override
  protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
    super.onTextChanged(text, start, lengthBefore, lengthAfter);
    ViewUtil.resizeText(this, originalTextSize, minTextSize);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    ViewUtil.resizeText(this, originalTextSize, minTextSize);
  }
}
