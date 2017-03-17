package mrh.framtest20160801.ui.frg;


import android.os.Bundle;
import android.widget.TextView;

import java.util.Map;

import mrh.framtest20160801.R;
import mrh.framtest20160801.configs.interfaces.Member;
import mrh.framtest20160801.configs.utils.JSONUtils;
import mrh.framtest20160801.configs.utils.LogUtils;
import mrh.framtest20160801.ui.base.BaseFragment;
import xutils.http.RequestParams;
import xutils.view.annotation.ViewInject;


/**
 * @author Yiku
 * @date 2016/7/20 17:33
 */
public class FragSecond extends BaseFragment {
    private Member member;
    @ViewInject(R.id.frag2_tv)
    private TextView tv_show;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_second;
    }

    @Override
    protected void initialized() {
        member = new Member();
    }

    @Override
    protected void requestData() {
        showProgressContent();
        member.info(this, "120029");
    }

    @Override
    public void onComplete(RequestParams params, String result) {
        super.onComplete(params, result);
        if (params.getUri().contains("info")) {
            Map<String, String> map = JSONUtils.parseDataToMap(result);
            tv_show.setText(map.toString());
            LogUtils.error(map.toString());
        }
    }
}
