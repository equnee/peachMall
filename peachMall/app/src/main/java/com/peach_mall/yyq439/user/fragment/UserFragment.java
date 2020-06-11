package com.peach_mall.yyq439.user.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.peach_mall.yyq439.R;
import com.peach_mall.yyq439.app.LoginActivity;
import com.peach_mall.yyq439.base.BaseFragment;
import com.peach_mall.yyq439.user.activity.MessageCenterActivity;
import com.peach_mall.yyq439.utils.BitmapUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


/**
 * 作用：用户中心的Fragment
 */

public class UserFragment extends BaseFragment implements View.OnClickListener {
    private ScrollView scrollview;
    private ImageButton ibUserIconAvator;
    private TextView tvUsername;
    private TextView tvAllOrder;
    private TextView tvUserPay;
    private TextView tvUserReceive;
    private TextView tvUserFinish;
    private TextView tvUserDrawback;
    private TextView tvUserLocation;
    private TextView tvUserCollect;
    private TextView tvUserCoupon;
    private TextView tvUserScore;
    private TextView tvUserPrize;
    private TextView tvUserTicket;
    private TextView tvUserInvitation;
    private TextView tvUserCallcenter;
    private TextView tvUserFeedback;
    private TextView tvUsercenter;
    private ImageButton ibUserSetting;
    private ImageButton ibUserMessage;

    private void findViews(View view) {
        scrollview = (ScrollView) view.findViewById(R.id.scrollview);
        ibUserIconAvator = (ImageButton) view.findViewById(R.id.ib_user_icon_avator);
        tvUsername = (TextView) view.findViewById(R.id.tv_username);
        tvAllOrder = (TextView) view.findViewById(R.id.tv_all_order);
        tvUserPay = (TextView) view.findViewById(R.id.tv_user_pay);
        tvUserReceive = (TextView) view.findViewById(R.id.tv_user_receive);
        tvUserFinish = (TextView) view.findViewById(R.id.tv_user_finish);
        tvUserDrawback = (TextView) view.findViewById(R.id.tv_user_drawback);
        tvUserLocation = (TextView) view.findViewById(R.id.tv_user_location);
        tvUserCollect = (TextView) view.findViewById(R.id.tv_user_collect);
        tvUserCoupon = (TextView) view.findViewById(R.id.tv_user_coupon);
        tvUserScore = (TextView) view.findViewById(R.id.tv_user_score);
        tvUserPrize = (TextView) view.findViewById(R.id.tv_user_prize);
        tvUserTicket = (TextView) view.findViewById(R.id.tv_user_ticket);
        tvUserInvitation = (TextView) view.findViewById(R.id.tv_user_invitation);
        tvUserCallcenter = (TextView) view.findViewById(R.id.tv_user_callcenter);
        tvUserFeedback = (TextView) view.findViewById(R.id.tv_user_feedback);
        tvUsercenter = (TextView) view.findViewById(R.id.tv_usercenter);
        ibUserSetting = (ImageButton) view.findViewById(R.id.ib_user_setting);
        ibUserMessage = (ImageButton) view.findViewById(R.id.ib_user_message);

        ibUserIconAvator.setOnClickListener(this);
        ibUserSetting.setOnClickListener(this);
        ibUserMessage.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == ibUserIconAvator) {
            // Handle clicks for ibUserIconAvator
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
        } else if (v == ibUserSetting) {
            // Handle clicks for ibUserSetting
            Toast.makeText(mContext, "设置", Toast.LENGTH_SHORT).show();
        } else if (v == ibUserMessage) {
            // Handle clicks for ibUserMessage
            Intent intent = new Intent(mContext, MessageCenterActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        findViews(view);
        tvUsercenter.setAlpha(0);
        return view;
    }

    @Override
    public void initData() {
        scrollview.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("Range")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int[] location = new int[2];
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:   //下滑是正，上滑是负
                        ibUserIconAvator.getLocationOnScreen(location); //初始状态为125，即最大值是125，全部不透明显示是（40？）
                        float i = (location[1] - 40) / 85f;
                        tvUsercenter.setAlpha(1 - i);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0) {
            String screen_name = data.getStringExtra("screen_name");
            String profile_image_url = data.getStringExtra("profile_image_url");

            Picasso.with(mContext).load(profile_image_url).transform(new Transformation() {
                @Override
                public Bitmap transform(Bitmap bitmap) {
                    //先对图片进行压缩
                    Bitmap zoom = BitmapUtils.zoom(bitmap, 90, 90);
                    //对请求回来的Bitmap进行圆形处理
                    Bitmap ciceBitMap = BitmapUtils.circleBitmap(zoom);
                    bitmap.recycle();
                    return ciceBitMap;
                }

                @Override
                public String key() {
                    return "";
                }
            }).into(ibUserIconAvator);

            tvUsername.setText(screen_name);
        }
    }
}
