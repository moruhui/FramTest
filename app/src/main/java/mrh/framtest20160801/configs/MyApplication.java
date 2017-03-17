package mrh.framtest20160801.configs;

/**
 * @author Yiku
 * @date 2016/5/9 13:29
 */

import android.app.Application;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import autolayout.config.AutoLayoutConifg;
import mrh.framtest20160801.BuildConfig;
import mrh.framtest20160801.configs.utils.ListUtils;
import mrh.framtest20160801.configs.utils.PreferencesUtils;
import xutils.x;


public class MyApplication extends Application {
    private final String PREF_USERINFO = "user_info";
    private AppManager appManager;
    private Map<String, String> userInfo;
    private Map<String, String> locationInfo;
//    private LocationClient mLocationClient;
//    private LocationListener locationListener;
    private static MyApplication application = null;

    public MyApplication() {
    }

    public static MyApplication getInstance() {
        return application;
    }

    public void onCreate() {
        super.onCreate();
        application = this;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        AutoLayoutConifg.getInstance().useDeviceSize().init(this);
        appManager = AppManager.getInstance();
        this.start();
        this.initUserInfo();
    }

    private void start() {
        Settings.displayWidth = Toolkit.getScreenWidth(this);
        Settings.displayHeight = Toolkit.getScreenHeight(this);
        Settings.cacheCompressPath = FileManager.getCompressFilePath();
        Settings.crashLogPath = FileManager.getCrashLogFilePath();
        Settings.voicePath = FileManager.getVoiceFilePath();
        (new File(Settings.cacheCompressPath)).mkdirs();
        (new File(Settings.crashLogPath)).mkdirs();
        (new File(Settings.voicePath)).mkdirs();
    }

    private void finish() {
        this.appManager.AppExit(this);
        System.gc();
    }

    private void initUserInfo() {
        this.userInfo = new HashMap();
        String keys = PreferencesUtils.getString(this, "user_info");
        if(!TextUtils.isEmpty(keys)) {
            String[] userInfos = keys.split(",");
            String[] var3 = userInfos;
            int var4 = userInfos.length;
            for(int var5 = 0; var5 < var4; ++var5) {
                String key = var3[var5];
                this.userInfo.put(key, PreferencesUtils.getString(this, key));
            }
        }

    }

    public Map<String, String> getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(Map<String, String> userInfo) {
        this.userInfo = userInfo;
        ArrayList keys = new ArrayList();
        Iterator iterator = this.userInfo.keySet().iterator();

        while(iterator.hasNext()) {
            String key = (String)iterator.next();
            keys.add(key);
            String value = (String)this.userInfo.get(key);
            PreferencesUtils.putString(this, key, value);
        }

        PreferencesUtils.putString(this, "user_info", ListUtils.join(keys));
    }

    public void setUserInfoItem(String key, String value) {
        this.userInfo.put(key, value);
        PreferencesUtils.putString(this, key, value);
    }

//    public void startBDLocation(LocationListener listener) {
//        this.locationListener = listener;
//        this.mLocationClient = new LocationClient(this.getApplicationContext());
////        this.mLocationClient.registerLocationListener(new my.LocListener());
//        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        option.setCoorType("bd09ll");
//        option.setIsNeedAddress(true);
//        this.mLocationClient.setLocOption(option);
//        this.mLocationClient.start();
//    }
//
//    public Map<String, String> getLocationInfo() {
//        return this.locationInfo;
//    }

//    private class LocListener implements BDLocationListener {
//        private LocListener() {
//        }
//
//        public void onReceiveLocation(BDLocation arg0) {
//            if(arg0 != null) {
////                WeApplication.this.locationInfo = new HashMap();
////                WeApplication.this.locationInfo.put("LATITUDE", String.valueOf(arg0.getLatitude()));
////                WeApplication.this.locationInfo.put("LONGITUDE", String.valueOf(arg0.getLongitude()));
////                if(arg0.hasAddr()) {
////                    WeApplication.this.locationInfo.put("ADDRESS", arg0.getAddrStr());
////                    WeApplication.this.locationInfo.put("CITY", arg0.getCity());
////                    WeApplication.this.locationInfo.put("DISTRICT", arg0.getDistrict());
////                    WeApplication.this.locationInfo.put("FLOOR", arg0.getFloor());
////                    WeApplication.this.locationInfo.put("PROVINCE", arg0.getProvince());
////                    WeApplication.this.locationInfo.put("STREET", arg0.getStreet());
////                    WeApplication.this.locationInfo.put("STREET_NUMBER", arg0.getStreetNumber());
////                }
////
////                if(WeApplication.this.locationListener != null) {
////                    WeApplication.this.locationListener.onReceiveLocation(arg0);
////                }
////
////                WeApplication.this.mLocationClient.stop();
//            }
//        }
//    }
}



