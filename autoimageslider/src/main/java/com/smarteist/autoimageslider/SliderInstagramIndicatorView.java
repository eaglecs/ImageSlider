package com.smarteist.autoimageslider;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.smarteist.autoimageslider.Transformations.AntiClockSpinTransformation;
import com.smarteist.autoimageslider.Transformations.Clock_SpinTransformation;
import com.smarteist.autoimageslider.Transformations.CubeInDepthTransformation;
import com.smarteist.autoimageslider.Transformations.CubeInRotationTransformation;
import com.smarteist.autoimageslider.Transformations.CubeInScalingTransformation;
import com.smarteist.autoimageslider.Transformations.CubeOutDepthTransformation;
import com.smarteist.autoimageslider.Transformations.CubeOutRotationTransformation;
import com.smarteist.autoimageslider.Transformations.CubeOutScalingTransformation;
import com.smarteist.autoimageslider.Transformations.DepthTransformation;
import com.smarteist.autoimageslider.Transformations.FadeTransformation;
import com.smarteist.autoimageslider.Transformations.FanTransformation;
import com.smarteist.autoimageslider.Transformations.FidgetSpinTransformation;
import com.smarteist.autoimageslider.Transformations.GateTransformation;
import com.smarteist.autoimageslider.Transformations.HingeTransformation;
import com.smarteist.autoimageslider.Transformations.HorizontalFlipTransformation;
import com.smarteist.autoimageslider.Transformations.PopTransformation;
import com.smarteist.autoimageslider.Transformations.SimpleTransformation;
import com.smarteist.autoimageslider.Transformations.SpinnerTransformation;
import com.smarteist.autoimageslider.Transformations.TossTransformation;
import com.smarteist.autoimageslider.Transformations.VerticalFlipTransformation;
import com.smarteist.autoimageslider.Transformations.VerticalShutTransformation;
import com.smarteist.autoimageslider.Transformations.ZoomOutTransformation;
import com.smarteist.autoimageslider.scrollingpagerindicator.ScrollingPagerIndicator;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class SliderInstagramIndicatorView extends FrameLayout {

    private final Handler mHandler = new Handler();
    private boolean isAutoCycle = true;
    private int scrollTimeInSec = 2;
    private CircularSliderHandle mCircularSliderHandle;
    private ScrollingPagerIndicator mPagerIndicator;
    private DataSetObserver mDataSetObserver;
    private PagerAdapter mPagerAdapter;
    private Runnable mSliderRunnable;
    private SliderPager mSliderPager;


    public SliderInstagramIndicatorView(Context context) {
        super(context);
        setupSlideView(context);
    }

    public SliderInstagramIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupSlideView(context);
    }

    public SliderInstagramIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupSlideView(context);
    }

    private void setupSlideView(Context context) {

        View wrapperView = LayoutInflater
                .from(context)
                .inflate(R.layout.slider_instagram_view, this, true);

        mSliderPager = wrapperView.findViewById(R.id.vp_slider_layout);
        mCircularSliderHandle = new CircularSliderHandle(mSliderPager);
        mSliderPager.addOnPageChangeListener(mCircularSliderHandle);
        mPagerIndicator = wrapperView.findViewById(R.id.pager_indicator);
    }

    public void setOnItemClickListener(SliderPager.OnItemClickListener onItemClickListener) {
        mSliderPager.setOnItemClickListener(onItemClickListener);
    }

    public void setOnIndicatorClickListener(ViewPager.OnPageChangeListener listener) {
        mSliderPager.addOnPageChangeListener(listener);
//        mPagerIndicator.setClickListener(listener);
    }

    public void setCurrentPageListener(CircularSliderHandle.CurrentPageListener listener) {
        mCircularSliderHandle.setCurrentPageListener(listener);
    }

    public void setSliderAdapter(final PagerAdapter pagerAdapter) {
        mPagerAdapter = pagerAdapter;
//        registerDataObserver();
        mSliderPager.setAdapter(pagerAdapter);
        mSliderPager.setOffscreenPageLimit(pagerAdapter.getCount() - 1);
        //setup with indicator
        mPagerIndicator.setDotCount(pagerAdapter.getCount());
        mPagerIndicator.attachToPager(mSliderPager);
//        mPagerIndicator.setDynamicCount(true);
    }

    private void registerDataObserver() {
        if (mDataSetObserver != null) {
            mPagerAdapter.unregisterDataSetObserver(mDataSetObserver);
        }
        mDataSetObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                mSliderPager.setOffscreenPageLimit(mPagerAdapter.getCount() - 1);
            }
        };

        mPagerAdapter.registerDataSetObserver(mDataSetObserver);
    }

    public PagerAdapter getSliderAdapter() {
        return mPagerAdapter;
    }

    public boolean isAutoCycle() {
        return isAutoCycle;
    }

    public void setAutoCycle(boolean autoCycle) {
        this.isAutoCycle = autoCycle;
        if (!isAutoCycle && mSliderRunnable != null) {
            mHandler.removeCallbacks(mSliderRunnable);
            mSliderRunnable = null;
        }
    }

    public int getScrollTimeInSec() {
        return scrollTimeInSec;
    }

    public void setScrollTimeInSec(int time) {
        scrollTimeInSec = time;
    }

    public void setSliderTransformAnimation(SliderAnimations animation) {

        switch (animation) {
            case ANTICLOCKSPINTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new AntiClockSpinTransformation());
                break;
            case CLOCK_SPINTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new Clock_SpinTransformation());
                break;
            case CUBEINDEPTHTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeInDepthTransformation());
                break;
            case CUBEINROTATIONTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeInRotationTransformation());
                break;
            case CUBEINSCALINGTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeInScalingTransformation());
                break;
            case CUBEOUTDEPTHTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeOutDepthTransformation());
                break;
            case CUBEOUTROTATIONTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeOutRotationTransformation());
                break;
            case CUBEOUTSCALINGTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new CubeOutScalingTransformation());
                break;
            case DEPTHTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new DepthTransformation());
                break;
            case FADETRANSFORMATION:
                mSliderPager.setPageTransformer(false, new FadeTransformation());
                break;
            case FANTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new FanTransformation());
                break;
            case FIDGETSPINTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new FidgetSpinTransformation());
                break;
            case GATETRANSFORMATION:
                mSliderPager.setPageTransformer(false, new GateTransformation());
                break;
            case HINGETRANSFORMATION:
                mSliderPager.setPageTransformer(false, new HingeTransformation());
                break;
            case HORIZONTALFLIPTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new HorizontalFlipTransformation());
                break;
            case POPTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new PopTransformation());
                break;
            case SIMPLETRANSFORMATION:
                mSliderPager.setPageTransformer(false, new SimpleTransformation());
                break;
            case SPINNERTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new SpinnerTransformation());
                break;
            case TOSSTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new TossTransformation());
                break;
            case VERTICALFLIPTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new VerticalFlipTransformation());
                break;
            case VERTICALSHUTTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new VerticalShutTransformation());
                break;
            case ZOOMOUTTRANSFORMATION:
                mSliderPager.setPageTransformer(false, new ZoomOutTransformation());
                break;
            default:
                mSliderPager.setPageTransformer(false, new SimpleTransformation());

        }

    }

    public void setCustomSliderTransformAnimation(ViewPager.PageTransformer animation) {
        mSliderPager.setPageTransformer(false, animation);
    }

    public void setSliderAnimationDuration(int duration) {
        mSliderPager.setScrollDuration(duration);
    }

    public void setCurrentPagePosition(int position, boolean smoothScroll) {

        if (getSliderAdapter() != null) {
            mSliderPager.setCurrentItem(position, smoothScroll);
        } else {
            throw new NullPointerException("Adapter not set");
        }
    }

    public int getCurrentPagePosition() {

        if (getSliderAdapter() != null) {
            return mSliderPager.getCurrentItem();
        } else {
            throw new NullPointerException("Adapter not set");
        }
    }

    public void setPagerIndicatorVisibility(boolean visibility) {
        if (visibility) {
            mPagerIndicator.setVisibility(VISIBLE);
        } else {
            mPagerIndicator.setVisibility(GONE);
        }
    }

    public void startAutoCycle() {

        if (mSliderRunnable != null) {
            mHandler.removeCallbacks(mSliderRunnable);
            mSliderRunnable = null;
        }

        mSliderRunnable = new Runnable() {
            @Override
            public void run() {

                try {
                    // check is on auto scroll mode
                    if (!isAutoCycle) {
                        return;
                    }

                    int currentPosition = mSliderPager.getCurrentItem();

                    if (currentPosition == getSliderAdapter().getCount() - 1) {
                        // if is last item return to the first position
                        mSliderPager.setCurrentItem(0, true);
                    } else {
                        // continue smooth transition between pager
                        mSliderPager.setCurrentItem(++currentPosition, true);
                    }

                } finally {
                    mHandler.postDelayed(this, scrollTimeInSec * 1000);
                }

            }
        };

        //Run the loop for the first time
        mHandler.postDelayed(mSliderRunnable, scrollTimeInSec * 1000);
    }

}
