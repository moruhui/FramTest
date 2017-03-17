package mrh.framtest20160801.configs.web;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import mrh.framtest20160801.configs.utils.JSONUtils;
import mrh.framtest20160801.configs.utils.ListUtils;
import mrh.framtest20160801.configs.utils.MapUtils;
import xutils.common.Callback;
import xutils.http.HttpMethod;
import xutils.http.RequestParams;
import xutils.x;

/**
 * @author Yiku
 * @date 2016/7/18 10:20
 */
public class ApiTool {
    public ApiTool() {
    }

    public Callback.Cancelable getApi(RequestParams params, ApiListener apiListener) {
        params.setMethod(HttpMethod.GET);
        return x.http().get(params, new ApiTool.DefaultRequestCallBack(apiListener));
    }

    public Callback.Cancelable postApi(RequestParams params, ApiListener apiListener) {
        params.setMethod(HttpMethod.POST);
        if (!ListUtils.isEmpty(params.getFileParams())) {
            params.setMultipart(true);
        }
        return x.http().post(params, new ApiTool.DefaultRequestCallBack(apiListener));
    }

    private Map<String, String> parseError(String json) {
        JSONObject jsonObject = null;
        if (json.startsWith("[") && json.endsWith("]")) {
            return null;
        } else {
            try {
                jsonObject = new JSONObject(json);
            } catch (JSONException var4) {
                return null;
            }
            String flag = jsonObject.optString("flag");
            return flag != null && flag.equals("error") ? JSONUtils.parseKeyAndValueToMap(json) : null;
        }
    }

    private class DefaultRequestCallBack implements Callback.CommonCallback<String> {
        private ApiListener apiListener;

        public DefaultRequestCallBack(ApiListener apiListener) {
            this.apiListener = apiListener;
        }


        public void onSuccess(RequestParams params, String result) {
            try {
                Map e = ApiTool.this.parseError(result);
                if (MapUtils.isEmpty(e)) {
                    if (this.apiListener != null) {
                        this.apiListener.onComplete(params, result);
                    }
                } else if (this.apiListener != null) {
                    this.apiListener.onError(e);
                }
            } catch (Exception var4) {
                if (this.apiListener != null) {
                    this.apiListener.onException(var4);
                }
            }

        }


//        @Override
//        public void onSuccess(String result) {
//            try {
//                Map e = ApiTool.this.parseError(result);
//                if (MapUtils.isEmpty(e)) {
//                    if (this.apiListener != null) {
//                        this.apiListener.onComplete(result);
//                    }
//                } else if (this.apiListener != null) {
//                    this.apiListener.onError(e);
//                }
//            } catch (Exception var4) {
//                if (this.apiListener != null) {
//                    this.apiListener.onException(var4);
//                }
//            }
//        }

        public void onError(Throwable ex, boolean isOnCallback) {
            if (this.apiListener != null) {
                this.apiListener.onException(ex);
            }

        }

        public void onCancelled(CancelledException cex) {
            if (this.apiListener != null) {
                this.apiListener.onCancelled(cex);
            }

        }

        public void onFinished() {
        }
    }
}

