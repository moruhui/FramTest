package mrh.framtest20160801.ui.frg;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mrh.framtest20160801.R;
import mrh.framtest20160801.configs.interfaces.Member;
import mrh.framtest20160801.configs.utils.JSONUtils;
import mrh.framtest20160801.configs.utils.LogUtils;
import mrh.framtest20160801.ui.base.BaseFragment;
import xutils.http.RequestParams;
import xutils.view.annotation.Event;
import xutils.view.annotation.ViewInject;


/**
 * @author Yiku
 * @date 2016/7/20 17:33
 */
public class FragThird extends BaseFragment {
    private Member member;
    @ViewInject(R.id.frag3_tv)
    private TextView tv_show;
    @ViewInject(R.id.frag3_tv_baidu)
    private TextView tvBaiDu;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_third;
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

    @Event({R.id.frag3_tv_baidu, R.id.frag3_tv_gaode})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.frag3_tv_baidu://百度地图测试
                if (isAvilible(getContext(), "com.baidu.BaiduMap")) {//传入指定应用包名
                    Intent intent = null;
                    try {
//                    intent = Intent.getIntent("intent://map/direction?origin=latlng:34.264642646862,108.95108518068|name:我家&destination=大雁塔&mode=driving®ion=西安&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                        intent = Intent.getIntent("intent://map/direction?" +
                                "origin=大柏树&" +
                                "destination=江湾镇" +
                                "&mode=driving&" +
                                "region=上海市" +
                                "&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                        startActivity(intent); //启动调用
                    } catch (URISyntaxException e) {
                        Log.e("intent", e.getMessage());
                    }
                } else {//未安装
                    //market为路径，id为包名
                    //显示手机上所有的market商店
                    Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
                break;
            case R.id.frag3_tv_gaode://高德地图测试
                if (isAvilible(getContext(), "com.autonavi.minimap")) {//传入指定应用包名
                    goToNaviActivity(getContext(),"test",null,"34.264642646862","108.95108518068","1","2");
                } else {//未安装
                    //market为路径，id为包名
                    //显示手机上所有的market商店
                    Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
                break;

        }
    }

    //获取手机上的软件比对看看是否安装着这款软件
    private boolean isAvilible(Context context, String packageName) {
        List<PackageInfo> packageInfos = context.getPackageManager().getInstalledPackages(0);
//        final PackageManager packageManager = context.getPackageManager();
//        //获取所有已安装程序的包信息
//        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 启动高德App进行导航
     * <h3>Version</h3> 1.0
     * <h3>CreateTime</h3> 2016/6/27,13:58
     * <h3>UpdateTime</h3> 2016/6/27,13:58
     * <h3>CreateAuthor</h3>
     * <h3>UpdateAuthor</h3>
     * <h3>UpdateInfo</h3> (此处输入修改内容,若无修改可不写.)
     *
     * @param sourceApplication 必填 第三方调用应用名称。如 amap
     * @param poiname           非必填 POI 名称
     * @param lat               必填 纬度
     * @param lon               必填 经度
     * @param dev               必填 是否偏移(0:lat 和 lon 是已经加密后的,不需要国测加密; 1:需要国测加密)
     * @param style             必填 导航方式(0 速度快; 1 费用少; 2 路程短; 3 不走高速；4 躲避拥堵；5 不走高速且避免收费；6 不走高速且躲避拥堵；7 躲避收费和拥堵；8 不走高速躲避收费和拥堵))
     */
    private void goToNaviActivity(Context context, String sourceApplication, String poiname, String lat, String lon, String dev, String style) {
        StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=")
                .append(sourceApplication);
        if (!TextUtils.isEmpty(poiname)) {
            stringBuffer.append("&poiname=").append(poiname);
        }
        stringBuffer.append("&lat=").append(lat)
                .append("&lon=").append(lon)
                .append("&dev=").append(dev)
                .append("&style=").append(style);

        Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(stringBuffer.toString()));
        intent.setPackage("com.autonavi.minimap");
        context.startActivity(intent);
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
