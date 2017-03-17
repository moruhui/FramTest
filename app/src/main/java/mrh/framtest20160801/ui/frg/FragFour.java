package mrh.framtest20160801.ui.frg;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mrh.framtest20160801.R;
import mrh.framtest20160801.ui.base.BaseFragment;
import mrh.framtest20160801.ui.pull.FastPullAty;
import xutils.view.annotation.Event;
import xutils.view.annotation.ViewInject;


/**
 * @author Yiku
 * @date 2016/7/20 17:33
 */
public class FragFour extends BaseFragment {
    @ViewInject(R.id.frg4_fastpull)
    private TextView tvFastPull;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_four;
    }

    @Override
    protected void initialized() {

    }

    @Override
    protected void requestData() {
    }

    @Event({R.id.frg4_fastpull})
    private void onMyClick(View v) {
        switch (v.getId()) {
            case R.id.frg4_fastpull:
                startActivity(FastPullAty.class,null);
                break;
        }

    }


}
