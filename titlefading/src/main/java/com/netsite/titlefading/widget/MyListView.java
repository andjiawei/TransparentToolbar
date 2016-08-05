package com.netsite.titlefading.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.netsite.titlefading.utils.Utils;


/**
 * Created by hrz on 2016/7/3.
 */
public class MyListView extends ListView implements AbsListView.OnScrollListener {

    private TransparentToolBar mTransparentToolBar;
    private FrameLayout mMarginView;
    private int resId;

    public MyListView(Context context) {
        super(context);
        init();
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnScrollListener(this);
    }

//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        super.onScrollChanged(l, t, oldl, oldt);
//        //将滚动不断变化的top值传递给ToolBar用于计算透明度
//        mTransparentToolBar.setChangeTop(t);
//    }
    /**
     * 注入Header
     */
    public void setHeader(int resId){
        this.resId=resId;
        mMarginView = new FrameLayout(getContext());
        //把图片宽度缩放到屏幕宽度，再按照比例缩放图片高度。有更好办法请告知
        int height = (int)Utils.getHeaderHeight(getContext(),resId);//拿到根据宽高比例缩放后顶部图片的高
        Bitmap bitmap = Utils.decodeSampledBitmapFromResource(getContext().getResources(), resId, Utils.getScreenWdith(getContext()), height);
        Drawable bitmapDrabable =new BitmapDrawable(bitmap);
        mMarginView.setBackgroundDrawable(bitmapDrabable);
        mMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        addHeaderView(mMarginView);
    }

    /**
     * 注入ToolBar
     */
    public void setTitleBar(TransparentToolBar titleBar) {
        mTransparentToolBar = titleBar;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        View topChild = view.getChildAt(0);
        if (topChild == mMarginView && resId!=0) {
            int top = topChild.getTop();
            //将滚动不断变化的top值传递给ToolBar用于计算透明度
            mTransparentToolBar.setChangeTop(-top);
        }
        if (topChild !=mMarginView && mTransparentToolBar!=null && resId!=0) {
            //将滚动不断变化的top值传递给ToolBar用于计算透明度
            mTransparentToolBar.setChangeTop(Utils.getHeaderHeight(getContext(),resId));
        }
    }
}
