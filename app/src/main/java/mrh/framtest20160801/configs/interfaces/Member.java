package mrh.framtest20160801.configs.interfaces;


import java.io.File;

import mrh.framtest20160801.configs.web.ApiListener;
import mrh.framtest20160801.configs.web.ApiTool;
import xutils.http.RequestParams;

/**
 * @author Yiku
 * @date 2016/7/18 14:22
 */
public class Member {
    private String medule = getClass().getSimpleName();

    /**
     * 上传头像
     *
     * @param apiListener
     * @param m_id
     * @param Head
     */
    public void saveHead(ApiListener apiListener, String m_id, String Head) {
        RequestParams params = new RequestParams(AppConfig.BASE_URL_DRINK5 + medule + "/saveHead");
        params.addBodyParameter("m_id", m_id);
        params.addBodyParameter("Head", new File(Head));
        ApiTool apiTool = new ApiTool();
        apiTool.postApi(params, apiListener);
    }

    /**
     * 用户信息
     *
     * @param apiListener
     * @param m_id
     */
    public void info(ApiListener apiListener, String m_id) {
        RequestParams params = new RequestParams(AppConfig.BASE_URL_DRINK5 + medule + "/info");
        params.addBodyParameter("m_id", m_id);
        ApiTool apiTool = new ApiTool();
        apiTool.postApi(params, apiListener);
    }

}
