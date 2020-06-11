package com.peach_mall.yyq439.app;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.peach_mall.yyq439.R;
import com.peach_mall.yyq439.base.BaseFragment;
import com.peach_mall.yyq439.community.fragment.CommunityFragment;
import com.peach_mall.yyq439.home.fragment.HomeFragment;
import com.peach_mall.yyq439.shoppingcart.fragment.ShoppingcartFragment;
import com.peach_mall.yyq439.type.fragment.TypeFragment;
import com.peach_mall.yyq439.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;


public class MainActivity extends FragmentActivity {

    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    private Fragment tempFragment;

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        rgMain = (RadioGroup) findViewById(R.id.rg_main);

        //ButterKnife.bind(this);

        initFragment();
        initListener();
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }
                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragment, baseFragment);  // (上次，本次）
            }
        });
        rgMain.check(R.id.rb_home);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingcartFragment());
        fragments.add(new UserFragment());
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0 ) {
            return fragments.get(position);
        }
        return null;
    }

    // 切换
    private void switchFragment (Fragment fromFragment, BaseFragment nextFragment ) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // 判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    // 隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    // 添加
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    // 隐藏
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}
