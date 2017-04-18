package com.bwei.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/*
* 作者：梁伟航 on 2017/3/13 21:08
* 类的用途：
*/
public class Recycler extends RecyclerView{
    public Recycler(Context context) {
        super(context);
    }

    public Recycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Recycler(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
