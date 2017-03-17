package autolayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import autolayout.utils.AutoUtils;
import autolayout.utils.DimenUtils;
import mrh.framtest20160801.R;

public class AutoTabLayout extends TabLayout {
    private static final int NO_VALID = -1;
    private int mTextSize;
    private boolean mTextSizeBaseWidth;

    public AutoTabLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public AutoTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTextSizeBaseWidth = false;
        this.initTextSizeBaseWidth(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabLayout, defStyleAttr, R.style.Widget_Design_TabLayout);
        int tabTextAppearance = a.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab);
        this.mTextSize = this.loadTextSizeFromTextAppearance(tabTextAppearance);
        a.recycle();
    }

    public static final int[] AutoTabLayout = {0x7f01009d};
    public static final int AutoTabLayout_auto_textSize_base_width = 0;

    private void initTextSizeBaseWidth(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, AutoTabLayout);
        this.mTextSizeBaseWidth = a.getBoolean(AutoTabLayout_auto_textSize_base_width, false);
        a.recycle();
    }

    private int loadTextSizeFromTextAppearance(int textAppearanceResId) {
        TypedArray a = this.getContext().obtainStyledAttributes(textAppearanceResId, R.styleable.TextAppearance);

        byte var3;
        try {
            if (DimenUtils.isPxVal(a.peekValue(R.styleable.TextAppearance_android_textSize))) {
                int var7 = a.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1);
                return var7;
            }

            var3 = -1;
        } finally {
            a.recycle();
        }

        return var3;
    }

    public void addTab(Tab tab, int position, boolean setSelected) {
        super.addTab(tab, position, setSelected);
        this.setUpTabTextSize(tab);
    }

    public void addTab(Tab tab, boolean setSelected) {
        super.addTab(tab, setSelected);
        this.setUpTabTextSize(tab);
    }

    private void setUpTabTextSize(Tab tab) {
        if (this.mTextSize != -1 && tab.getCustomView() == null) {
            ViewGroup tabGroup = (ViewGroup) this.getChildAt(0);
            ViewGroup tabContainer = (ViewGroup) tabGroup.getChildAt(tab.getPosition());
            TextView textView = (TextView) tabContainer.getChildAt(1);
            if (!AutoUtils.autoed(textView)) {
                boolean autoTextSize = false;
                int autoTextSize1;
                if (this.mTextSizeBaseWidth) {
                    autoTextSize1 = AutoUtils.getPercentWidthSize(this.mTextSize);
                } else {
                    autoTextSize1 = AutoUtils.getPercentHeightSize(this.mTextSize);
                }

                textView.setTextSize(0, (float) autoTextSize1);
            }
        }
    }
}
