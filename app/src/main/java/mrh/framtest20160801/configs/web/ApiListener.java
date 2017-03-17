package mrh.framtest20160801.configs.web;


import java.util.Map;

import xutils.common.Callback;
import xutils.http.RequestParams;

/**
 * @author Yiku
 * @date 2016/7/18 10:11
 */
public interface ApiListener {
    void onCancelled(Callback.CancelledException var1);

    void onComplete(RequestParams params,String var2);

    void onError(Map<String, String> var1);

    void onException(Throwable var1);
}
