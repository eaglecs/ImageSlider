package com.smarteist.imageslider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderInstagramIndicatorView;
import com.smarteist.autoimageslider.SliderView;

public class MainActivity extends AppCompatActivity {

    SliderInstagramIndicatorView sliderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderView = findViewById(R.id.imageSlider);

        final SliderAdapterExample adapter = new SliderAdapterExample(this);

        sliderView.setSliderAdapter(adapter);
        sliderView.startAutoCycle();
        sliderView.setAutoCycle(true);
        sliderView.setScrollTimeInSec(3); //set scroll delay in seconds :
//        sliderView.setIndicatorAnimation(IndicatorAnimations.COLOR); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
//        sliderView.setSliderIndicatorSelectedColor(Color.WHITE);
//        sliderView.setSliderIndicatorUnselectedColor(Color.GRAY);
//        sliderView.setSliderTransformAnimation(SliderAnimations.TOSSTRANSFORMATION); //CUBEOUTDEPTHTRANSFORMATION
//        sliderView.setSliderIndicatorRadius(10);
        sliderView.setCurrentPagePosition(0, false);
//        sliderView.setPagerIndicatorVisibility(false);

        sliderView.setOnIndicatorClickListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                sliderView.setCurrentPagePosition(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

}
