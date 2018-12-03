package support.com.dzh.myapplication.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可以设置禁止滑动的 ViewPager(单向禁止：左滑动)
 * 			核心方法：setScrollble()
 * @author alan
 *
 */
public class CanotSlidingViewpager extends ViewPager {

    /**
     * 上一次x坐标
     */
    private float beforeX;



    public CanotSlidingViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public CanotSlidingViewpager(Context context) {

        super(context);
    }

    private boolean isCanScroll = true;


    @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {

//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                beforeX = ev.getX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float motionValue = ev.getX() - beforeX;
//                if (motionValue < 0) {
//                    return isCanScroll;
//                }
//                beforeX = ev.getX();
//
//                break;
//            default:
//
//                break;
//        }

        return false;
    }


    public boolean isScrollble() {
        return isCanScroll;
    }
    /**
     * 设置 是否可以滑动
     * @param isCanScroll
     */
    public void setScrollble(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }
}
