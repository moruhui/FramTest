package autolayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.reflect.Field;

import autolayout.AutoLayoutInfo;
import autolayout.utils.AutoLayoutHelper;
import autolayout.utils.AutoUtils;
import autolayout.utils.DimenUtils;
import mrh.framtest20160801.R;
import mrh.framtest20160801.configs.AppManager;
import mrh.framtest20160801.ui.base.BaseActivity;

public class AutoToolbar extends Toolbar {
    private static final int NO_VALID = -1;
    private int mTextSize;
    private int mSubTextSize;
    private final AutoLayoutHelper mHelper;

    public AutoToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mHelper = new AutoLayoutHelper(this);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Toolbar, defStyleAttr, R.style.Widget_AppCompat_Toolbar);
        int titleTextAppearance = a.getResourceId(R.styleable.Toolbar_titleTextAppearance, R.style.TextAppearance_Widget_AppCompat_Toolbar_Title);
        int subtitleTextAppearance = a.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, R.style.TextAppearance_Widget_AppCompat_Toolbar_Subtitle);
        this.mTextSize = this.loadTextSizeFromTextAppearance(titleTextAppearance);
        this.mSubTextSize = this.loadTextSizeFromTextAppearance(subtitleTextAppearance);
        TypedArray menuA = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AppCompatTheme, defStyleAttr, R.style.ThemeOverlay_AppCompat);
        int menuTextAppearance = menuA.getResourceId(R.styleable.AppCompatTheme_actionBarTheme, R.style.ThemeOverlay_AppCompat_ActionBar);
        int menuTextSize = this.loadTextSizeFromTextAppearance(menuTextAppearance);
        if(this.mTextSize == -1) {
            this.mTextSize = menuTextSize;
        }

        if(this.mSubTextSize == -1) {
            this.mSubTextSize = menuTextSize;
        }

        a.recycle();
        menuA.recycle();
    }

    public AutoToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoToolbar(Context context) {
        this(context, (AttributeSet)null);
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

    public void hide() {
        this.setVisibility(GONE);
    }

    public boolean isShowing() {
        return this.getVisibility() == GONE;
    }

    public void setTitle(CharSequence title) {
        super.setTitle(title);
        ((BaseActivity) AppManager.getInstance().getTopActivity()).setSupportActionBar(this);
    }

    private void setUpTitleTextSize() {
        CharSequence title = this.getTitle();
        if(!TextUtils.isEmpty(title) && this.mTextSize != -1) {
            this.setUpTitleTextSize("mTitleTextView", this.mTextSize);
        }

        CharSequence subtitle = this.getSubtitle();
        if(!TextUtils.isEmpty(subtitle) && this.mSubTextSize != -1) {
            this.setUpTitleTextSize("mSubtitleTextView", this.mSubTextSize);
        }

    }

    private void setUpTitleTextSize(String name, int val) {
        try {
            Field e = this.getClass().getSuperclass().getDeclaredField(name);
            e.setAccessible(true);
            TextView textView = (TextView)e.get(this);
            if(textView != null) {
                int autoTextSize = AutoUtils.getPercentHeightSize(val);
                textView.setTextSize(0, (float)autoTextSize);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!this.isInEditMode()) {
            this.setUpTitleTextSize();
            this.mHelper.adjustChildren();
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public AutoToolbar.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AutoToolbar.LayoutParams(this.getContext(), attrs);
    }

    public static class LayoutParams extends android.support.v7.widget.Toolbar.LayoutParams implements AutoLayoutHelper.AutoLayoutParams {
        private AutoLayoutInfo mDimenLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.mDimenLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
        }

        public AutoLayoutInfo getAutoLayoutInfo() {
            return this.mDimenLayoutInfo;
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }
    }
}