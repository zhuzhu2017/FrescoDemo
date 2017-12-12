package com.allen.frescodemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.allen.frescodemo.callback.FrescoBitmapCallback;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * fresco图片加载工具
 * Created by allen on 2017/12/12.
 */

public class FrescoUtil {
    /*单例对象*/
    private static FrescoUtil INSTANCE;

    /*构造器*/
    private FrescoUtil() {
    }

    /*获取单例对象*/
    public static FrescoUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (FrescoUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FrescoUtil();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 设置通用图片数据，图片宽高都已经设定
     *
     * @param imageUrl   图片Url
     * @param draweeView 显示的图片draweeView
     */
    public void setCommonImageData(String imageUrl, SimpleDraweeView draweeView) {
        if (imageUrl == null || draweeView == null) return;
        Uri uri = Uri.parse(imageUrl);
        draweeView.setImageURI(uri);
    }

    /**
     * 设置通用图片数据，图片宽高重定义
     *
     * @param imageUrl    图片Url
     * @param draweeView  显示的图片draweeView
     * @param imageWidth  重定义的图片宽度
     * @param imageHeight 重定义的图片高度
     */
    public void setCommonImageData(String imageUrl, SimpleDraweeView draweeView, int imageWidth, int imageHeight) {
        if (imageUrl == null || draweeView == null) return;
        //设定SimpleDraweeView宽高
        ViewGroup.LayoutParams laoutParams = new ViewGroup.LayoutParams(imageWidth, imageHeight);
        draweeView.setLayoutParams(laoutParams);
        //解析Uri
        Uri uri = Uri.parse(imageUrl);
        draweeView.setImageURI(uri);
    }

    /**
     * 设置Gif显示，需要监听状态，当listener不为空时需要设置autoPlay为false
     *
     * @param imageUrl   图片Url
     * @param draweeView 图片
     * @param autoPlay   是否自动播放
     * @param listener   加载监听
     */
    public void setCommonGifData(String imageUrl, SimpleDraweeView draweeView, boolean autoPlay, ControllerListener listener) {
        if (imageUrl == null || draweeView == null) return;
        Uri uri = Uri.parse(imageUrl);
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(listener == null && autoPlay)
                .setControllerListener(listener)
                .build();
        draweeView.setController(controller);
    }

    /**
     * 从缓存中获取图片
     *
     * @param imageUrl 图片url
     * @return 缓存中的图片
     */
    private Bitmap getBitmapFromCache(String imageUrl) {
        Bitmap bitmap = null;
        try {
            Uri uri = Uri.parse(imageUrl);
            FileBinaryResource resource = (FileBinaryResource) Fresco.getImagePipelineFactory()
                    .getMainFileCache()
                    .getResource(new SimpleCacheKey(uri.toString()));
            File file = resource.getFile();
            bitmap = BitmapFactory.decodeFile(file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 获取Bitmap
     *
     * @param imageUrl 图片Url
     * @param callback 获取图片结果回调
     */
    public void getImageBitmap(String imageUrl, final FrescoBitmapCallback<Bitmap> callback) {
        if (imageUrl == null) return;
        //先从缓存中获取bitmap
        Bitmap bitmapFormCache = getBitmapFromCache(imageUrl);
        if (bitmapFormCache != null) {
            if (callback != null) callback.onSuccess(Uri.parse(imageUrl), bitmapFormCache);
            return;
        }
        //如果缓存中没有，进行异步获取
        final Uri uri = Uri.parse(imageUrl);
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .build();
        ImagePipeline imagePipeline = ImagePipelineFactory.getInstance().getImagePipeline();
        final DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(request, null);
        if (dataSource == null) return;
        try {
            dataSource.subscribe(new BaseBitmapDataSubscriber() {
                @Override
                protected void onNewResultImpl(@Nullable final Bitmap bitmap) {
                    if (bitmap == null || callback == null) return;
                    if (!bitmap.isRecycled()) {   //bitmap还没有被回收
                        handlerBackgroundTask(new Callable<Bitmap>() {
                            @Override
                            public Bitmap call() throws Exception {
                                final Bitmap resultBitmap = bitmap.copy(bitmap.getConfig(), bitmap.isMutable());
                                if (resultBitmap != null && !resultBitmap.isRecycled())
                                    postResult(resultBitmap, uri, callback);
                                return resultBitmap;
                            }
                        });
                    }
                }

                @Override
                protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> getBitmapFromCache) {
                    if (callback != null) {
                        callback.onFailure(uri, dataSource.getFailureCause());
                    }
                }

                @Override
                public void onCancellation(DataSource<CloseableReference<CloseableImage>> dataSource) {
                    super.onCancellation(dataSource);
                    if (callback != null) callback.onCancel(uri);
                }
            }, UiThreadImmediateExecutorService.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataSource.close();
        }
    }

    /**
     * @param callable Callable
     * @param <T>      T
     * @return Future
     */
    private static ExecutorService executeBackgroundTask = Executors.newSingleThreadExecutor();

    private static <T> void handlerBackgroundTask(Callable<T> callable) {
        executeBackgroundTask.submit(callable);
    }

    /**
     * 回调UI线程中去
     *
     * @param result   result
     * @param uri      uri
     * @param callback FrescoBitmapCallback
     * @param <T>      T
     */
    private static <T> void postResult(final T result, final Uri uri, final FrescoBitmapCallback<T> callback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(uri, result);
            }
        });
    }

}
