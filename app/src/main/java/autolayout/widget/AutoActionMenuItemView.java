package autolayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.view.menu.ActionMenuItemView;
import android.util.AttributeSet;

import autolayout.utils.AutoUtils;
import autolayout.utils.DimenUtils;
import mrh.framtest20160801.R;

public class AutoActionMenuItemView extends ActionMenuItemView {
    private static final int NO_VALID = -1;
    private int mMenuTextSize;

    public AutoActionMenuItemView(Context context) {
        this(context, (AttributeSet)null);
    }

    public AutoActionMenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoActionMenuItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AppCompatTheme, defStyle, R.style.ThemeOverlay_AppCompat);
        int menuTextAppearance = a.getResourceId(R.styleable.AppCompatTheme_actionBarTheme, R.style.ThemeOverlay_AppCompat_ActionBar);
        this.mMenuTextSize = this.loadTextSizeFromTextAppearance(menuTextAppearance);
        a.recycle();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!this.isInEditMode()) {
            this.setUpTitleTextSize();
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int loadTextSizeFromTextAppearance(int textAppearanceResId) {
        TypedArray a = this.getContext().obtainStyledAttributes(textAppearanceResId, R.styleable.TextAppearance);

        int var3;
        try {
            if(!DimenUtils.isPxVal(a.peekValue(R.styleable.TextAppearance_android_textSize))) {
                byte var7 = -1;
                return var7;
            }

            var3 = a.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1);
        } finally {
            a.recycle();
        }

        return var3;
    }

    private void setUpTitleTextSize() {
        if(this.mMenuTextSize != -1) {
            int autoTextSize = AutoUtils.getPercentHeightSize(this.mMenuTextSize);
            this.setTextSize(0, (float)autoTextSize);
        }
    }
}
