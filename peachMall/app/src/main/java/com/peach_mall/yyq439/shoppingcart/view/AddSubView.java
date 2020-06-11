package com.peach_mall.yyq439.shoppingcart.view;


import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.peach_mall.yyq439.R;

public class AddSubView extends LinearLayout implements View.OnClickListener {

    private final Context mContext;
    private TextView iv_sub;
    private TextView tv_value;
    private TextView iv_add;
    private int value = 1;
    private int minValue = 1;
    private int maxValue = 6;
    private OnNumberChangeListener onNumberChangeListener;

    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        View.inflate(context, R.layout.add_sub_view, this);
        iv_sub = (TextView)findViewById(R.id.iv_sub);
        tv_value = (TextView)findViewById(R.id.tv_value);
        iv_add = (TextView)findViewById(R.id.iv_add);

        int  value  = getValue();
        setValue(value);
        //设置点击事件
        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_sub:
                subNumber();
                break;
            case R.id.iv_add:
                addNumber();
                break;
        }
    }

    private void addNumber() {
        if(value < maxValue) {
            value++;
        }
        setValue(value);

        if(onNumberChangeListener != null) {
            onNumberChangeListener.onNumberChange(value);
        }

    }

    private void subNumber() {
        if(value > minValue) {
            value --;
        }
        setValue(value);

        if(onNumberChangeListener != null) {
            onNumberChangeListener.onNumberChange(value);
        }
    }

    public void setValue(int value) {
        this.value=value;
        tv_value.setText(value+"");
    }

    private int getValue() {
        String valueStr = tv_value.getText().toString().trim();
        if(!TextUtils.isEmpty(valueStr)){
            value = Integer.parseInt(valueStr);
        }
        return  value;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    //-----------------------------------------------
    // 监听
    public interface OnNumberChangeListener {
        void onNumberChange(int value);
    }
    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener){
        this.onNumberChangeListener = onNumberChangeListener;
    }

}
