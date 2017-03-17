package autolayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableRow;

import autolayout.AutoLayoutInfo;
import autolayout.utils.AutoLayoutHelper;

public class AutoTableRow extends TableRow {
    private AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoTableRow(Context context) {
        super(context);
    }

    public AutoTableRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!this.isInEditMode()) {
            this.mHelper.adjustChildren();
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public AutoTableRow.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AutoTableRow.LayoutParams(this.getContext(), attrs);
    }

    public static class LayoutParams extends android.widget.TableRow.LayoutParams implements AutoLayoutHelper.AutoLayoutParams {
        private AutoLayoutInfo mAutoLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
        }

        public AutoLayoutInfo getAutoLayoutInfo() {
            return this.mAutoLayoutInfo;
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
