package com.allen.frescodemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.allen.frescodemo.R;
import com.allen.frescodemo.activity.draweeui.DraweeInXmlActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * drawee使用
 * Created by allen on 2017/12/11.
 */

public class DraweeActivity extends AppCompatActivity {
    @BindView(R.id.btn_in_xml)
    Button btnInXml;
    @BindView(R.id.btn_in_java)
    Button btnInJava;
    @BindView(R.id.btn_progressbar)
    Button btnProgressbar;
    @BindView(R.id.btn_scale)
    Button btnScale;
    @BindView(R.id.btn_circle)
    Button btnCircle;
    @BindView(R.id.btn_controllerbuilder)
    Button btnControllerbuilder;
    @BindView(R.id.btn_jpeg)
    Button btnJpeg;
    @BindView(R.id.btn_animation)
    Button btnAnimation;
    @BindView(R.id.btn_multi_pic)
    Button btnMultiPic;
    @BindView(R.id.btn_download_listener)
    Button btnDownloadListener;
    @BindView(R.id.btn_scale_rotate)
    Button btnScaleRotate;
    @BindView(R.id.btn_modify_pic)
    Button btnModifyPic;
    @BindView(R.id.btn_image_request)
    Button btnImageRequest;
    @BindView(R.id.btn_custom_view)
    Button btnCustomView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawee);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_in_xml, R.id.btn_in_java, R.id.btn_progressbar, R.id.btn_scale, R.id.btn_circle, R.id.btn_controllerbuilder, R.id.btn_jpeg, R.id.btn_animation, R.id.btn_multi_pic, R.id.btn_download_listener, R.id.btn_scale_rotate, R.id.btn_modify_pic, R.id.btn_image_request, R.id.btn_custom_view})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_in_xml:
                intent = new Intent(this,DraweeInXmlActivity.class);
                break;
            case R.id.btn_in_java:
                break;
            case R.id.btn_progressbar:
                break;
            case R.id.btn_scale:
                break;
            case R.id.btn_circle:
                break;
            case R.id.btn_controllerbuilder:
                break;
            case R.id.btn_jpeg:
                break;
            case R.id.btn_animation:
                break;
            case R.id.btn_multi_pic:
                break;
            case R.id.btn_download_listener:
                break;
            case R.id.btn_scale_rotate:
                break;
            case R.id.btn_modify_pic:
                break;
            case R.id.btn_image_request:
                break;
            case R.id.btn_custom_view:
                break;
        }
        if(intent != null) startActivity(intent);
    }
}
