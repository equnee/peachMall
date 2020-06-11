package com.peach_mall.yyq439.type.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.peach_mall.yyq439.R;
import com.peach_mall.yyq439.base.BaseFragment;

public class TypeFragment extends BaseFragment {


    private static final String TAG = TypeFragment.class.getSimpleName();
    private TextView textView;
    private ImageView imageView;

    @Override
    public View initView() {
        Log.e(TAG, "分类的视图被初始化了");
//        textView = new TextView(mContext);
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextSize(25);
//        textView.setTextColor(Color.RED);
        imageView = new ImageView(mContext);
        return imageView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "分类的数据被初始化了");
//        textView.setText("分类");
        imageView.setImageResource(R.drawable.type_back);
    }
}
