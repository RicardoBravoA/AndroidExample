package com.rba.mylibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.rba.mylibrary.adapter.ViewPagerAdapter;
import com.rba.mylibrary.widget.StepperViewPager;

import java.util.ArrayList;
import java.util.List;

public class Stepper extends LinearLayout implements ViewPager.OnPageChangeListener {

    private StepperViewPager vpAdvance;
    private List<Fragment> listFragment = new ArrayList<>();

    private AppCompatButton btnBack;
    private AppCompatButton btnNext;
    private ViewPagerAdapter adapter = null;
    private ProgressBar pbAdvance;
    private int colorProgressBar;

    private int currentPosition = 0;

    public Stepper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = inflate(context, R.layout.stepper_layout, this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Stepper);

        colorProgressBar = typedArray.getColor(R.styleable.Stepper_colorProgress,
                ContextCompat.getColor(context, R.color.colorAccent));
        typedArray.recycle();

        btnBack = view.findViewById(R.id.btn_back);
        btnNext = view.findViewById(R.id.btn_next);
        pbAdvance = view.findViewById(R.id.pb_advance);
        vpAdvance = view.findViewById(R.id.vp_advance);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpAdvance.setCurrentItem(currentPosition - 1, true);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpAdvance.setCurrentItem(currentPosition + 1, true);
            }
        });

        setColorProgressBar(colorProgressBar);

    }

    private void loadData() {
        if (getContext() instanceof AppCompatActivity) {
            if (adapter == null) {
                adapter = new ViewPagerAdapter(((AppCompatActivity) getContext()).getSupportFragmentManager());
            }

            pbAdvance.setMax(listFragment.size());
            pbAdvance.setProgress(1);

            adapter.setData(listFragment);
            vpAdvance.setAdapter(adapter);
            vpAdvance.setOffscreenPageLimit(listFragment.size());
            adapter.notifyDataSetChanged();
            vpAdvance.addOnPageChangeListener(this);
        } else {
            throw new IllegalArgumentException("Error");
        }
    }

    public void setData(List<Fragment> listFragment) {
        this.listFragment = listFragment;

        loadData();

    }

    public void setColorProgressBar(int color) {
        this.colorProgressBar = color;
        pbAdvance.getProgressDrawable().setColorFilter(colorProgressBar, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        //Do nothing
    }

    @Override
    public void onPageSelected(int i) {
        currentPosition = i;
        pbAdvance.setProgress(i + 1);
        btnBack.setEnabled(i != 0);
        btnNext.setEnabled(i != listFragment.size() - 1);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        //Do nothing
    }

}
