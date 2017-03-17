package mrh.framtest20160801.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.Map;

import mrh.framtest20160801.R;
import mrh.framtest20160801.configs.AppManager;
import mrh.framtest20160801.configs.MyApplication;
import mrh.framtest20160801.configs.utils.LogUtils;
import mrh.framtest20160801.configs.web.ApiListener;
import xutils.common.Callback;
import xutils.http.RequestParams;
import xutils.x;

/**
 * @author Yiku
 * @date 2016/7/18 10:20
 */
public abstract class BaseFragment extends Fragment implements ApiListener, IBaseFragement {
    private FrameLayout titlebar;
    private FrameLayout content;
    private View divider;
    private View progress;
    private View error;
    private ProgressDialog progressDialog;
    private AlertDialog.Builder builder;
    protected MyApplication application;
    protected Object mDataIn;
    private boolean isShowContent;
    protected boolean hasAnimiation = true;
    private boolean isShowing = false;

    public BaseFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.preliminary();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fgt_base, container, false);
        this.initControls(layout);
        this.setBasicContentView(this.getLayoutResId());
        this.setCustomTitlebar();
        return layout;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        x.view().inject(this, this.getView());
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.requestData();
    }

    public void onResume() {
        super.onResume();
        this.showStatus("onResume");
    }

    public void onPause() {
        super.onPause();
    }

    public void onComeIn(Object data) {
        this.mDataIn = data;
        this.showStatus("onComeIn");
    }

    public void onLeave() {
        this.showStatus("onLeave");
    }

    public void onBack(Object data) {
        this.showStatus("onBack");
    }

    public void onStop() {
        super.onStop();
        this.showStatus("onStop");
    }

    private void showStatus(String status) {
        Log.d("test", String.format("%s %s", new Object[]{this.getClass().getName(), status}));
    }

    private void initControls(View layout) {
        this.titlebar = (FrameLayout) layout.findViewById(R.id.fgt_titlebar);
        this.content = (FrameLayout) layout.findViewById(R.id.content);
        this.divider = layout.findViewById(R.id.content_divider);
        this.content.setOnClickListener((View.OnClickListener) null);
        this.progress = View.inflate(this.getActivity(), R.layout.loading_content, (ViewGroup) null);
        this.progress.setOnClickListener((View.OnClickListener) null);
        this.error = View.inflate(this.getActivity(), R.layout.layout_error, (ViewGroup) null);
        this.error.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BaseFragment.this.requestData();
                BaseFragment.this.content.removeView(BaseFragment.this.error);
            }
        });
        this.progressDialog = new ProgressDialog(this.getActivity(), R.layout.loading_dialog);
        this.progressDialog.setIndeterminate(true);
        this.progressDialog.setCancelable(true);
        this.progressDialog.setCanceledOnTouchOutside(false);
    }

    private void setBasicContentView(int layoutResId) {
        LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutResId, (ViewGroup) null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        view.setLayoutParams(layoutParams);
        this.content.addView(view);
    }

    private void setCustomTitlebar() {
        if (this.getTitlebarResId() != 0) {
            this.divider.setVisibility(View.VISIBLE);
            View view = this.content.findViewById(this.getTitlebarResId());
            ViewParent viewParent = view.getParent();
            if (viewParent instanceof ViewGroup) {
                ((ViewGroup) viewParent).removeView(view);
                this.titlebar.addView(view);
                view.getLayoutParams().height = -1;
            }
        } else {
            this.titlebar.setVisibility(View.GONE);
            this.divider.setVisibility(View.GONE);
        }

    }

    private void preliminary() {
        application = (MyApplication) getActivity().getApplication();
        this.initialized();
    }

    protected int getTitlebarResId() {
        return 0;
    }

    protected abstract int getLayoutResId();

    protected abstract void initialized();

    protected abstract void requestData();

    public FrameLayout getContent() {
        return this.content;
    }

    public BaseActivity getContext() {
        return (BaseActivity) this.getActivity();
    }

    public void onClick2(View v) {
    }

    public void onItemClick2(AdapterView<?> parent, View view, int position, long id) {
    }


    protected void startActivity(Class<?> className, Bundle options) {
        Intent intent = new Intent(this.getActivity(), className);
        if (options != null) {
            intent.putExtras(options);
        }

        this.startActivity(intent);
    }


    protected void showProgressContent() {
        if (!this.isShowing) {
            this.isShowContent = true;
            this.isShowing = true;
            this.content.addView(this.progress);
        }
    }

    protected void removeProgressContent() {
        if (this.isShowing) {
            this.isShowing = false;
            this.isShowContent = false;
            this.content.removeView(this.progress);
        }

    }

    protected void showProgressDialog() {
        this.isShowContent = false;
        if (!this.progressDialog.isShowing()) {
            this.progressDialog.show();
            this.progressDialog.setContentView(R.layout.loading_dialog);
        }

    }

    protected void removeProgressDialog() {
        if (this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }

    }

    public void onComplete(RequestParams params, String result) {
        this.removeProgressContent();
        this.removeProgressDialog();
    }

    protected void showToast(String error) {
        Toast.makeText(AppManager.getInstance().getTopActivity(), error, Toast.LENGTH_SHORT).show();
    }

    public void onError(Map<String, String> error) {
        this.showToast((String) error.get("message"));
        this.removeProgressContent();
        this.removeProgressDialog();
    }

    public void onException(Throwable ex) {
        if (isShowContent) {
            this.content.addView(error);
        } else if (!(ex instanceof SocketException) && !(ex instanceof InterruptedIOException) && !(ex instanceof UnknownHostException) && !(ex instanceof UnknownServiceException)) {
            showToast("服务器未知错误！");
        } else {
            showToast("网络不给力，请检查网络！");
        }
        LogUtils.error("内部代码出错，检查onComplete/onError中的代码");
        ex.printStackTrace();
        this.removeProgressContent();
        this.removeProgressDialog();
    }

    public void onCancelled(Callback.CancelledException cex) {
        cex.printStackTrace();
    }
}