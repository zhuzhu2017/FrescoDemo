package com.allen.frescodemo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.allen.frescodemo.Constants;
import com.allen.frescodemo.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 基础使用页面
 * Created by allen on 2017/12/11.
 */

public class BasicUseActivity extends AppCompatActivity {
    @BindView(R.id.btn_get_pic)
    Button btnGetPic;
    @BindView(R.id.iv_image)
    SimpleDraweeView ivImage;

    private Uri uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_use);
        ButterKnife.bind(this);
        uri = Uri.parse(Constants.IMAGE_URL);
    }

    @OnClick(R.id.btn_get_pic)
    public void onViewClicked() {
        ivImage.setImageURI(uri);
    }
}
