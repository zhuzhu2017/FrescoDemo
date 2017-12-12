package com.allen.frescodemo.activity.draweeui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.allen.frescodemo.R;

/**
 * drawee in xml
 * fresco:fadeDuration="300"
 * fresco:actualImageScaleType="focusCrop"
 * fresco:placeholderImage="@color/wait_color"
 * fresco:placeholderImageScaleType="fitCenter"
 * fresco:failureImage="@drawable/error"
 * fresco:failureImageScaleType="centerInside"
 * fresco:retryImage="@drawable/retrying"
 * fresco:retryImageScaleType="centerCrop"
 * fresco:progressBarImage="@drawable/progress_bar"
 * fresco:progressBarImageScaleType="centerInside"
 * fresco:progressBarAutoRotateInterval="1000"
 * fresco:backgroundImage="@color/blue"
 * fresco:overlayImage="@drawable/watermark"
 * fresco:pressedStateOverlayImage="@color/red"
 * fresco:roundAsCircle="false"
 * fresco:roundedCornerRadius="1dp"
 * fresco:roundTopLeft="true"
 * fresco:roundTopRight="false"
 * fresco:roundBottomLeft="false"
 * fresco:roundBottomRight="true"
 * fresco:roundWithOverlayColor="@color/corner_color"
 * fresco:roundingBorderWidth="2dp"
 * fresco:roundingBorderColor="@color/border_color"
 * Created by allen on 2017/12/11.
 */

public class DraweeInXmlActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawee_in_xml);
    }
}
