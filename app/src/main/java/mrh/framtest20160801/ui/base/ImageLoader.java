package mrh.framtest20160801.ui.base;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.File;

import mrh.framtest20160801.configs.utils.ScreenUtils;
import xutils.common.Callback;
import xutils.image.ImageOptions;
import xutils.x;

public class ImageLoader {

    private ImageOptions options;

    public ImageLoader() {
        options = ImageOptionsBuilder.DEFAULT.build();
    }


    public ImageLoader(int type) {
        switch (type) {
            case 1:
                options = ImageOptionsBuilder.DEFAULT.build();
                break;
            case 2:
                options = ImageOptionsBuilder.DEFAULT_FADEIN.build();
                break;
            case 3:
                options = ImageOptionsBuilder.CIRCULAR.build();
                break;
            case 4:
                options = ImageOptionsBuilder.CIRCULAR_FADEIN.build();
                break;
            case 5:
                options = ImageOptionsBuilder.RADIUS.build();
                break;
            case 6:
                options = ImageOptionsBuilder.RADIUS_FADEIN.build();
                break;
        }
    }

    public void setImageOptions(ImageOptions options) {
        this.options = options;
    }

    /**
     * 异步加载图片
     *
     * @param imageView
     * @param url
     */
    public void disPlay(ImageView imageView, String url) {
        x.image().bind(imageView, url, options);
    }

    public void disPlay(ImageView imageView, String url, Callback.CommonCallback<Drawable> callback) {
        x.image().bind(imageView, url, callback);
    }

    /**
     * 加载图片
     *
     * @param url      assets://test.gif , file:/sdcard/test.gif , file:///sdcard/test.gif , /sdcard/test.gif , new File("/sdcard/test.gif").toURI().toString()
     * @param options
     * @param callback
     * @return
     */
    public Callback.Cancelable loadDrawable(String url, ImageOptions options, Callback.CommonCallback<Drawable> callback) {
        return x.image().loadDrawable(url, options == null ? this.options : options, callback);
    }

    /**
     * 加载文件
     *
     * @param url      assets://test.gif , file:/sdcard/test.gif , file:///sdcard/test.gif , /sdcard/test.gif , new File("/sdcard/test.gif").toURI().toString()
     * @param options
     * @param callback
     * @return
     */
    public Callback.Cancelable loadFile(String url, ImageOptions options, Callback.CacheCallback<File> callback) {
        return x.image().loadFile(url, options == null ? this.options : options, callback);
    }

    public enum ImageOptionsBuilder {
        DEFAULT(new ImageOptions.Builder()),
        DEFAULT_FADEIN(new ImageOptions.Builder().setFadeIn(true).setImageScaleType(ImageView.ScaleType.CENTER_CROP).setUseMemCache(true)),
        CIRCULAR(new ImageOptions.Builder().setCircular(true).setImageScaleType(ImageView.ScaleType.CENTER_CROP).setUseMemCache(true)),
        CIRCULAR_FADEIN(new ImageOptions.Builder().setCircular(true).setFadeIn(true).setImageScaleType(ImageView.ScaleType.CENTER_CROP).setUseMemCache(true)),
        RADIUS(new ImageOptions.Builder().setRadius(ScreenUtils.dpToPxInt(5)).setImageScaleType(ImageView.ScaleType.CENTER_CROP).setUseMemCache(true)),
        RADIUS_FADEIN(new ImageOptions.Builder().setRadius(ScreenUtils.dpToPxInt(5)).setFadeIn(true).setImageScaleType(ImageView.ScaleType.CENTER_CROP).setUseMemCache(true));

        private ImageOptions.Builder builder;

        ImageOptionsBuilder(ImageOptions.Builder builder) {
            this.builder = builder;
        }

        public ImageOptions.Builder getStyle() {
            return builder;
        }

        public ImageOptions build() {
            return builder.build();
        }

        public ImageOptions.Builder setSize(int width, int height) {
            builder.setSize(ScreenUtils.dpToPxInt(width), ScreenUtils.dpToPxInt(height));
            return builder;
        }

        public ImageOptions.Builder setRadius(int radius) {
            builder.setRadius(ScreenUtils.dpToPxInt(radius));
            return builder;
        }

        public ImageOptions.Builder setImageScaleType(ImageView.ScaleType imageScaleType) {
            builder.setImageScaleType(imageScaleType);
            return builder;
        }

        public ImageOptions.Builder setLoadingDrawable(Drawable loadingDrawable) {
            builder.setLoadingDrawable(loadingDrawable);
            return builder;
        }

        public ImageOptions.Builder setLoadingDrawableId(int loadingDrawableId) {
            builder.setLoadingDrawableId(loadingDrawableId);
            return builder;
        }

        public ImageOptions.Builder setFailureDrawable(Drawable failureDrawable) {
            builder.setFailureDrawable(failureDrawable);
            return builder;
        }

        public ImageOptions.Builder setFailureDrawableId(int failureDrawableId) {
            builder.setFailureDrawableId(failureDrawableId);
            return builder;
        }

        public ImageOptions.Builder setCircular(boolean circular) {
            builder.setCircular(circular);
            return builder;
        }

        public ImageOptions.Builder setIgnoreGif(boolean ignoreGif) {
            builder.setIgnoreGif(ignoreGif);
            return builder;
        }
    }
}


