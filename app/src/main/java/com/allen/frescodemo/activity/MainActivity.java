package com.allen.frescodemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.allen.frescodemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_basic_use)
    Button btnBasicUse;
    @BindView(R.id.btn_drawee)
    Button btnDrawee;
    @BindView(R.id.btn_image_pipeline)
    Button btnImagePipeline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_basic_use, R.id.btn_drawee, R.id.btn_image_pipeline})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_basic_use:    //基础使用
                intent = new Intent(this, BasicUseActivity.class);
                break;
            case R.id.btn_drawee:   //drawee使用
                intent = new Intent(this, DraweeActivity.class);
                break;
            case R.id.btn_image_pipeline:   //image pipeline使用
                break;
        }
        if (intent != null) startActivity(intent);
    }
}
