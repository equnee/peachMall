package com.peach_mall.yyq439.community.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.peach_mall.yyq439.R;
import com.peach_mall.yyq439.base.BaseFragment;

public class CommunityFragment extends BaseFragment {

    private static final String TAG = CommunityFragment.class.getSimpleName();
    private TextView textView;
    private ImageView imageView;

    @Override
    public View initView() {
        Log.e(TAG, "发现视图被初始化了");
        imageView = new ImageView(mContext);
        return imageView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "发现的数据被初始化了");
        imageView.setImageResource(R.drawable.community_back);
    }
}
