package mrh.framtest20160801.configs.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Yiku
 * @date 2016/7/18 10:23
 */
public class JSONUtils {
    public static boolean isPrintException = true;

    public JSONUtils() {
    }

    public static Long getLong(JSONObject jsonObject, String key, Long defaultValue) {
        if(jsonObject != null && !StringUtils.isEmpty(key)) {
            try {
                return Long.valueOf(jsonObject.getLong(key));
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static Long getLong(String jsonData, String key, Long defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        } else {
            try {
                JSONObject e = new JSONObject(jsonData);
                return getLong(e, key, defaultValue);
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue;
            }
        }
    }

    public static long getLong(JSONObject jsonObject, String key, long defaultValue) {
        return getLong(jsonObject, key, Long.valueOf(defaultValue)).longValue();
    }

    public static long getLong(String jsonData, String key, long defaultValue) {
        return getLong(jsonData, key, Long.valueOf(defaultValue)).longValue();
    }

    public static Integer getInt(JSONObject jsonObject, String key, Integer defaultValue) {
        if(jsonObject != null && !StringUtils.isEmpty(key)) {
            try {
                return Integer.valueOf(jsonObject.getInt(key));
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static Integer getInt(String jsonData, String key, Integer defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        } else {
            try {
                JSONObject e = new JSONObject(jsonData);
                return getInt(e, key, defaultValue);
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue;
            }
        }
    }

    public static int getInt(JSONObject jsonObject, String key, int defaultValue) {
        return getInt(jsonObject, key, Integer.valueOf(defaultValue)).intValue();
    }

    public static int getInt(String jsonData, String key, int defaultValue) {
        return getInt(jsonData, key, Integer.valueOf(defaultValue)).intValue();
    }

    public static Double getDouble(JSONObject jsonObject, String key, Double defaultValue) {
        if(jsonObject != null && !StringUtils.isEmpty(key)) {
            try {
                return Double.valueOf(jsonObject.getDouble(key));
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static Double getDouble(String jsonData, String key, Double defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        } else {
            try {
                JSONObject e = new JSONObject(jsonData);
                return getDouble(e, key, defaultValue);
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue;
            }
        }
    }

    public static double getDouble(JSONObject jsonObject, String key, double defaultValue) {
        return getDouble(jsonObject, key, Double.valueOf(defaultValue)).doubleValue();
    }

    public static double getDouble(String jsonData, String key, double defaultValue) {
        return getDouble(jsonData, key, Double.valueOf(defaultValue)).doubleValue();
    }

    public static String getString(JSONObject jsonObject, String key, String defaultValue) {
        if(jsonObject != null && !StringUtils.isEmpty(key)) {
            try {
                return jsonObject.getString(key);
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static String getString(String jsonData, String key, String defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        } else {
            try {
                JSONObject e = new JSONObject(jsonData);
                return getString(e, key, defaultValue);
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue;
            }
        }
    }

    public static String[] getStringArray(JSONObject jsonObject, String key, String[] defaultValue) {
        if(jsonObject != null && !StringUtils.isEmpty(key)) {
            try {
                JSONArray e = jsonObject.getJSONArray(key);
                if(e == null) {
                    return defaultValue;
                } else {
                    String[] value = new String[e.length()];

                    for(int i = 0; i < e.length(); ++i) {
                        value[i] = e.getString(i);
                    }

                    return value;
                }
            } catch (JSONException var6) {
                if(isPrintException) {
                    var6.printStackTrace();
                }

                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static String[] getStringArray(String jsonData, String key, String[] defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        } else {
            try {
                JSONObject e = new JSONObject(jsonData);
                return getStringArray(e, key, defaultValue);
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue;
            }
        }
    }

    public static JSONObject getJSONObject(JSONObject jsonObject, String key, JSONObject defaultValue) {
        if(jsonObject != null && !StringUtils.isEmpty(key)) {
            try {
                return jsonObject.getJSONObject(key);
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static JSONObject getJSONObject(String jsonData, String key, JSONObject defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        } else {
            try {
                JSONObject e = new JSONObject(jsonData);
                return getJSONObject(e, key, defaultValue);
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue;
            }
        }
    }

    public static JSONArray getJSONArray(JSONObject jsonObject, String key, JSONArray defaultValue) {
        if(jsonObject != null && !StringUtils.isEmpty(key)) {
            try {
                return jsonObject.getJSONArray(key);
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public static JSONArray getJSONArray(String jsonData, String key, JSONArray defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        } else {
            try {
                JSONObject e = new JSONObject(jsonData);
                return getJSONArray(e, key, defaultValue);
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue;
            }
        }
    }

    public static boolean getBoolean(JSONObject jsonObject, String key, Boolean defaultValue) {
        if(jsonObject != null && !StringUtils.isEmpty(key)) {
            try {
                return jsonObject.getBoolean(key);
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue.booleanValue();
            }
        } else {
            return defaultValue.booleanValue();
        }
    }

    public static boolean getBoolean(String jsonData, String key, Boolean defaultValue) {
        if(StringUtils.isEmpty(jsonData)) {
            return defaultValue.booleanValue();
        } else {
            try {
                JSONObject e = new JSONObject(jsonData);
                return getBoolean(e, key, defaultValue);
            } catch (JSONException var4) {
                if(isPrintException) {
                    var4.printStackTrace();
                }

                return defaultValue.booleanValue();
            }
        }
    }

    public static Map<String, String> getMap(JSONObject jsonObject, String key) {
        return parseKeyAndValueToMap(getString((JSONObject)jsonObject, key, (String)null));
    }

    public static Map<String, String> getMap(String jsonData, String key) {
        if(jsonData == null) {
            return null;
        } else if(jsonData.length() == 0) {
            return new HashMap();
        } else {
            try {
                JSONObject e = new JSONObject(jsonData);
                return getMap(e, key);
            } catch (JSONException var3) {
                if(isPrintException) {
                    var3.printStackTrace();
                }

                return null;
            }
        }
    }

    public static Map<String, String> parseKeyAndValueToMap(JSONObject sourceObj) {
        if(sourceObj == null) {
            return null;
        } else {
            HashMap keyAndValueMap = new HashMap();
            Iterator iter = sourceObj.keys();

            while(iter.hasNext()) {
                String key = (String)iter.next();
                MapUtils.putMapNotEmptyKey(keyAndValueMap, key, getString(sourceObj, key, ""));
            }

            return keyAndValueMap;
        }
    }

    public static Map<String, String> parseKeyAndValueToMap(String source) {
        if(StringUtils.isEmpty(source)) {
            return null;
        } else {
            try {
                JSONObject e = new JSONObject(source);
                return parseKeyAndValueToMap(e);
            } catch (JSONException var2) {
                if(isPrintException) {
                    var2.printStackTrace();
                }

                return null;
            }
        }
    }

    public static ArrayList<Map<String, String>> parseKeyAndValueToMapList(String source) {
        if(StringUtils.isEmpty(source)) {
            return null;
        } else if(!source.startsWith("[") && !source.endsWith("]")) {
            return null;
        } else {
            try {
                ArrayList e = new ArrayList();
                JSONArray jsonArray = new JSONArray(source);

                for(int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    e.add(parseKeyAndValueToMap(jsonObject));
                }

                return e;
            } catch (JSONException var5) {
                if(isPrintException) {
                    var5.printStackTrace();
                }

                return null;
            }
        }
    }

    public static Map<String, String> parseDataToMap(String source) {
        if(StringUtils.isEmpty(source)) {
            return null;
        } else {
            try {
                JSONObject e = new JSONObject(source);
                return parseKeyAndValueToMap(e.getJSONObject("data"));
            } catch (JSONException var2) {
                if(isPrintException) {
                    var2.printStackTrace();
                }

                return null;
            }
        }
    }

    public static ArrayList<Map<String, String>> parseDataToMapList(String source) {
        if(StringUtils.isEmpty(source)) {
            return null;
        } else {
            try {
                ArrayList e = new ArrayList();
                JSONObject jsonObject = new JSONObject(source);
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                for(int i = 0; i < jsonArray.length(); ++i) {
                    e.add(parseKeyAndValueToMap(jsonArray.getJSONObject(i)));
                }

                return e;
            } catch (JSONException var5) {
                if(isPrintException) {
                    var5.printStackTrace();
                }

                return null;
            }
        }
    }
}

