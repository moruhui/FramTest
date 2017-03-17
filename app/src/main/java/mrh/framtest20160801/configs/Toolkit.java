package mrh.framtest20160801.configs;

import android.content.Context;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yiku
 * @date 2016/7/18 9:45
 */
public class Toolkit {
    public Toolkit() {
    }

    //获取IP地址
    public static String getLocalIpAddress() {
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();

            while (e.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface) e.nextElement();
                Enumeration enumIpAddr = intf.getInetAddresses();

                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public static void copy(Context context, String content) {
//        ClipboardManager cmb = (ClipboardManager)context.getSystemService("clipboard");
//        cmb.setText(content.trim());
    }

    public static String paste(Context context) {
//        ClipboardManager cmb = (ClipboardManager)context.getSystemService("clipboard");
//        return cmb.getText().toString().trim();
        return "";
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isEmail(String strEmail) {
        String strPattern = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    public static boolean isMobile(String mobiles) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,1-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(mobiles);
        return matcher.matches();
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getStatusBarHeight(Context context) {
        Class c = null;
        Object obj = null;
        Field field = null;
        boolean x = false;
        byte statusBarHeight = 0;

        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            int x1 = Integer.parseInt(field.get(obj).toString());
            int statusBarHeight1 = context.getResources().getDimensionPixelSize(x1);
            return statusBarHeight1;
        } catch (Exception var7) {
            var7.printStackTrace();
            return statusBarHeight;
        }
    }

    public static String getConfigProperties(String key) {
        String value = "";
        Properties props = new Properties();
        InputStream iStream = Toolkit.class.getResourceAsStream("/assets/config.properties");

        try {
            props.load(iStream);
            value = props.getProperty(key);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return value;
    }

    public static int getBitmapRes(Context context, String resName) {
        try {
            String localThrowable = context.getPackageName();
            Class localClass = Class.forName(localThrowable + ".R$drawable");
            return getResId(localClass, resName);
        } catch (Throwable var4) {
            return 0;
        }
    }

    public static int getViewRes(Context context, String resName) {
        try {
            String localThrowable = context.getPackageName();
            Class localClass = Class.forName(localThrowable + ".R$id");
            return getResId(localClass, resName);
        } catch (Throwable var4) {
            return 0;
        }
    }

    public static int getStringRes(Context context, String resName) {
        try {
            String localThrowable = context.getPackageName();
            Class localClass = Class.forName(localThrowable + ".R$string");
            return getResId(localClass, resName);
        } catch (Throwable var4) {
            return 0;
        }
    }

    private static int getResId(Class<?> paramClass, String paramString) {
        int i = 0;
        if (paramString != null) {
            String str = paramString.toLowerCase();

            try {
                Field localThrowable = paramClass.getField(str);
                localThrowable.setAccessible(true);
                i = ((Integer) localThrowable.get((Object) null)).intValue();
            } catch (Throwable var5) {
                i = 0;
            }
        }

        return i;
    }

    public static String getJson(List<Map<String, String>> list) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");

        for (int i = 0; i < list.size(); ++i) {
            Map object = (Map) list.get(i);
            sb.append("{");
            Iterator iterator = object.keySet().iterator();

            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                sb.append("\"" + key + "\":\"" + (String) object.get(key) + "\"");
                if (iterator.hasNext()) {
                    sb.append(",");
                }
            }

            sb.append("}");
            if (i != list.size() - 1) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}

