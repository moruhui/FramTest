package autolayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import autolayout.AutoLayoutActivity;

public class AutoLayoutWidgetActivity extends AutoLayoutActivity {
    private static final String ACTION_MENU_ITEM_VIEW = "android.support.v7.view.menu.ActionMenuItemView";
    private static final String TAB_LAYOUT = "android.support.design.widget.TabLayout";

    public AutoLayoutWidgetActivity() {
    }

    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Object view = null;
        if(name.equals("android.support.v7.view.menu.ActionMenuItemView")) {
            view = new AutoActionMenuItemView(context, attrs);
        }

        if(name.equals("android.support.design.widget.TabLayout")) {
            view = new AutoTabLayout(context, attrs);
        }

        return (View)(view != null?view:super.onCreateView(name, context, attrs));
    }
}
