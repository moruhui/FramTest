package mrh.framtest20160801.ui.base;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.Map;

import autolayout.AutoLayoutActivity;
import autolayout.widget.AutoToolbar;
import mrh.framtest20160801.R;
import mrh.framtest20160801.configs.AppManager;
import mrh.framtest20160801.configs.MyApplication;
import mrh.framtest20160801.configs.utils.LogUtils;
import mrh.framtest20160801.configs.web.ApiListener;
import xutils.common.Callback;
import xutils.http.RequestParams;
import xutils.view.annotation.ViewInject;
import xutils.x;

/**
 * @author Yiku
 * @date 2016/7/18 10:20
 */
public abstract class BaseActivity extends AutoLayoutActivity implements ApiListener {
    @ViewInject(R.id.toolbar)
    protected AutoToolbar mActionBar;
    protected MyApplication application;

    public BaseActivity() {
    }

    protected FrameLayout content;
    protected View divider;
    protected View error;
    protected View progress;
    protected ProgressDialog progressDialog;
    private boolean isShowing = false;
    protected boolean isShowContent;
//    protected BaseFragment currentFragment;
//    private List<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionBar = (AutoToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBar);
        AppManager.getInstance().addActivity(this);
        setContentView(R.layout.aty_base);
        initControls();
        setBasicContentView(getLayoutResId());
        x.view().inject(this);
        preliminary();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mActionBar.isShowing()) {
          divider.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().killActivity(this);
    }

    protected void initControls() {
        content = (FrameLayout) findViewById(R.id.content);
        divider = findViewById(R.id.content_divider);
        error = View.inflate(this, R.layout.layout_error, null);
        progress = View.inflate(this, R.layout.loading_content, null);
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.this.requestData();
                BaseActivity.this.content.removeView(BaseActivity.this.error);
            }
        });
        progressDialog = new ProgressDialog(this, R.layout.loading_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        super.getClass().getName();
        System.out.println(super.getClass().getName());
        Log.e("", "super.getClass().getName();");
        LogUtils.error(super.getClass().getName() + super.getClass().getSimpleName());

    }

    private void preliminary() {
        application = (MyApplication) getApplication();
        initialized();
        requestData();
    }

    protected int getTitlebarResId() {
        return 0;
    }

    protected abstract int getLayoutResId();

    protected int getFragmentContainerId() {
        return 0;
    }

    protected abstract void initialized();

    protected abstract void requestData();


    public FrameLayout getContent() {
        return this.content;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setBasicContentView(int layoutResId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View main = inflater.inflate(layoutResId, (ViewGroup) null);
        content.addView(main);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        main.setLayoutParams(layoutParams);
    }

    protected void startActivity(Class<?> className, Bundle options) {
        Intent intent = new Intent(this, className);
        if (options != null) {
            intent.putExtras(options);
        }
        this.startActivity(intent);
    }
//
//    public void pushFragmentToBackStatck(Class<?> cls, Object data) {
//        FragmentParam param = new FragmentParam();
//        param.cls = cls;
//        param.data = data;
//        param.addToBackStack = true;
//        this.processFragement(param);
//    }
//
//    public void addFragment(Class<?> cls, Object data) {
//        FragmentParam param = new FragmentParam();
//        param.cls = cls;
//        param.data = data;
//        param.addToBackStack = false;
//        this.processFragement(param);
//    }
//
//    public void replaceFragment(Class<?> cls, Object data) {
//        FragmentParam param = new FragmentParam();
//        param.cls = cls;
//        param.data = data;
//        param.type = FragmentParam.TYPE.REPLACE;
//        param.addToBackStack = false;
//        this.processFragement(param);
//    }
//
//    public void goToFragment(Class<?> cls, Object data) {
//        if (cls != null) {
//            BaseFragment fragment = (BaseFragment) this.getSupportFragmentManager().findFragmentByTag(cls.toString());
//            if (fragment != null) {
//                currentFragment = fragment;
//                fragment.onBack(data);
//            }
//
//            this.getSupportFragmentManager().popBackStackImmediate(cls.toString(), 0);
//        }
//    }
//
//    public void popTopFragment(Object data) {
//        FragmentManager fm = this.getSupportFragmentManager();
//        fm.popBackStackImmediate();
//        this.currentFragment = null;
//        int cnt = fm.getBackStackEntryCount();
//        String name = fm.getBackStackEntryAt(cnt - 1).getName();
//        this.currentFragment = (BaseFragment) fm.findFragmentByTag(name);
//        this.currentFragment.onBack(data);
//    }

//    public void popToRoot(Object data) {
//        FragmentManager fm = this.getSupportFragmentManager();
//
//        while (fm.getBackStackEntryCount() > 1) {
//            fm.popBackStackImmediate();
//        }
//        this.popTopFragment(data);
//    }
//
//    protected String getFragmentTag(FragmentParam param) {
//        StringBuilder sb = new StringBuilder(param.cls.toString());
//        return sb.toString();
//    }
//
//    private void processFragement(FragmentParam param) {
//        int containerId = getFragmentContainerId();
//        Class cls = param.cls;
//        if (cls != null) {
//            try {
//                String e = this.getFragmentTag(param);
//                BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(e);
//                if (fragment == null) {
//                    fragment = (BaseFragment) cls.newInstance();
//                }
//                fragment.onComeIn(param.data);
//                if (this.currentFragment != null)
//                    this.currentFragment.onLeave();
//                if (this.fragments == null)
//                    this.fragments = new ArrayList();
//                ListUtils.addDistinctEntry(fragments, fragment);
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                if (param.type != FragmentParam.TYPE.ADD) {
//                    ft.replace(containerId, fragment, e);
//                } else if (!fragment.isAdded()) {
//                    ft.add(containerId, fragment, e);
//                } else {
//                    Iterator var7 = fragments.iterator();
//                    while (var7.hasNext()) {
//                        BaseFragment lastFragment = (BaseFragment) var7.next();
//                        ft.hide(lastFragment);
//                    }
//                    currentFragment.onPause();
//                    ft.show(fragment);
//                    fragment.onResume();
//                }
//                currentFragment = fragment;
//                if (param.addToBackStack) {
//                    ft.addToBackStack(e);
//                }
//                ft.commitAllowingStateLoss();
//            } catch (InstantiationException var9) {
//                var9.printStackTrace();
//            } catch (IllegalAccessException var10) {
//                var10.printStackTrace();
//            }
//
//        }
//    }

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

    @Override
    public void onComplete(RequestParams params, String result) {
        removeProgressContent();
        removeProgressDialog();
    }

    @Override
    public void onError(Map<String, String> result) {
        showToast((String) result.get("message"));
        removeProgressContent();
        removeProgressDialog();
    }

    protected void showToast(String error) {
        Toast.makeText(AppManager.getInstance().getTopActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
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
        removeProgressContent();
        removeProgressDialog();
    }

    @Override
    public void onCancelled(Callback.CancelledException cex) {
        cex.printStackTrace();
    }

}
