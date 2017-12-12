package com.allen.frescodemo.callback;

import android.net.Uri;

/**
 * Fresco获取Bitmap回调
 * Created by allen on 2017/12/12.
 */

public interface FrescoBitmapCallback<T> {
    void onSuccess(Uri uri, T result);

    void onFailure(Uri uri, Throwable throwable);

    void onCancel(Uri uri);
}
