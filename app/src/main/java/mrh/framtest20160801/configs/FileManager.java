package mrh.framtest20160801.configs;

import android.os.Environment;

import java.io.File;

import xutils.x;

/**
 * @author Yiku
 * @date 2016/7/18 9:19
 */
public class FileManager {
    public static final String SDCARD_FOLDER_NAME = "YIKU";
    public static final String DISK_CACHE_DIR_NAME = "Bitmap_Cache";
    public static final String THUMB_CACHE = "Bitmap_Cache_Thumb";

    public FileManager() {
    }

    public static String getSaveFilePath() {
        return getRootFilePath() + "YIKU" + File.separator + "picture_cache" + File.separator;
    }

    public static String getCompressFilePath() {
        return getRootFilePath() + "YIKU" + File.separator + "compress_cache" + File.separator;
    }

    public static String getCrashLogFilePath() {
        return getRootFilePath() + "YIKU" + File.separator + "crash_log" + File.separator;
    }

    public static String getVoiceFilePath() {
        return getRootFilePath() + "TooCMS" + File.separator + "voice" + File.separator;
    }

    public static String getDownloadPath() {
        return getRootFilePath() + "YIKU" + File.separator + "download" + File.separator;
    }

    public static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        return status.equals("mounted");
    }

    public static String getRootFilePath() {
        return hasSDCard()?Environment.getExternalStorageDirectory().getAbsolutePath() + "/":Environment.getDataDirectory().getAbsolutePath() + "/data/";
    }

    public static void clearMemCache() {
        x.image().clearMemCache();
    }

    public static void clearCacheFiles() {
        x.image().clearCacheFiles();
    }
}

