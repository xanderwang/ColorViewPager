package com.xandy.colorviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.nineoldandroids.animation.ArgbEvaluator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by wangxiaoyang on 15-6-25.
 */
public class ColorViewPager extends ViewPager {

    private static final int WHITE 		= 0xFFFFFFFF;
    private static final int RED 		= 0xFFFA8072;
    private static final int YELLO 		= 0xFFFFA54F;
    private static final int GREEN 		= 0xFF008B8B;
    private static final int BLACK 		= 0xFF223344;
    private static final int GRAY 		= 0xFF787878;
    private static final int BLUE 		= 0xFF8080FF;

    private int[] BG_COLORS = { GREEN, RED, YELLO, GRAY , BLACK };

    private static final int DURATION 	= 1000;
    private int lastScrollPage = 0;

    /** 改变背景的动画对象 */
    private ValueAnimator colorAnim = null;

    private OnPageChangeListener mlistener;

    private OnPageChangeListener mPagerListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if( null != mlistener ) {
                mlistener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
            if( lastScrollPage != position ) {
                calculateColors(position);
            }
            lastScrollPage = position;
            seek(positionOffset);
        }

        @Override
        public void onPageSelected(int position) {
            if( null != mlistener ) {
                mlistener.onPageSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if( null != mlistener ) {
                mlistener.onPageScrollStateChanged(state);
            }
        }
    } ;

    public ColorViewPager(Context context) {
        super(context);
        initData();
    }

    public ColorViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData() {
        super.addOnPageChangeListener(mPagerListener);
        setCurrentItem(0,true);
        calculateColors(0);
        seek(0);
    }

    @Override
    public void addOnPageChangeListener(OnPageChangeListener listener) {
        mlistener = listener;
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mlistener = listener;
    }

    /**
     * 执行动画到指定的时间
     * @param seekTime 动画执行结束的时候的时间
     */
    private void seek(int seekTime) {
        if (colorAnim == null) {
            calculateColors(0);
        }
        colorAnim.setCurrentPlayTime(seekTime);
    }

    /**
     * 执行动画到指定的进度
     * @param seekProgress
     */
    private void seek(float seekProgress) {
        int realSeekTime = (int) (seekProgress * DURATION ) ;
        seek(realSeekTime);
    }

    /**
     * 颜色的计算方法
     * @param page
     * @return 当前页的颜色和下一页的颜色
     */
    private void calculateColors( int page  ) {
        int[] colors = new int[2];
        colors[0] = BG_COLORS[ page % BG_COLORS.length ];
        colors[1] = BG_COLORS[ ( page + 1 ) % BG_COLORS.length ];
        createAnimation(colors);
    }

    /**
     * 根据指定的颜色来创建颜色动画
     * @param colors 指定的颜色
     */
    private void createAnimation(int... colors) {
        colorAnim = ObjectAnimator.ofInt(this, "backgroundColor", colors);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setDuration(DURATION);
    }

    /**
     * 设置背景颜色
     * @param colors
     */
    public void setBgColors( int[] colors ) {
        BG_COLORS = colors;
    }

}
