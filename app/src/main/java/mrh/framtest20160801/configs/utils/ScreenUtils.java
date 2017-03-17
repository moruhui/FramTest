package mrh.framtest20160801.configs.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * @author Yiku
 * @date 2016/7/18 14:57
 */

public class ScreenUtils {
    public ScreenUtils() {
    }

    public static float dpToPx(float dp) {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }

    public static float pxToDp(float px) {
        return px / Resources.getSystem().getDisplayMetrics().density;
    }

    public static int dpToPxInt(float dp) {
        return (int)(dpToPx(dp) + 0.5F);
    }

    public static int pxToDpInt(Context context, float px) {
        return (int)(pxToDp(px) + 0.5F);
    }
}