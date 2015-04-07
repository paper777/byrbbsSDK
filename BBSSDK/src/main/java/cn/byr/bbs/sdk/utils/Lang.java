package cn.byr.bbs.sdk.utils;

import android.content.Context;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by ALSO on 2015/3/31.
 */
public class Lang {
        public static final int string_loading = 1;
        public static final int string_network_not_available = 2;
        private static final String LOADING_EN = "Loading...";
        private static final String LOADING_ZH_CN = "加载中...";
        private static final String NETWORK_NOT_AVAILABLE_EN = "Network is not available";
        private static final String NETWORK_NOT_AVAILABLE_ZH_CN = "当前网络不可用...";
        private static final HashMap<Locale, SparseArray<String>> sLanguageMap;

        static {
            sLanguageMap = new HashMap();
            SparseArray stringMap = new SparseArray();
            stringMap.put(string_loading, LOADING_ZH_CN);
            stringMap.put(string_network_not_available, NETWORK_NOT_AVAILABLE_ZH_CN);
            sLanguageMap.put(Locale.SIMPLIFIED_CHINESE, stringMap);
            stringMap = new SparseArray();
            stringMap.put(string_loading, LOADING_EN);
            stringMap.put(string_network_not_available, NETWORK_NOT_AVAILABLE_EN);
            sLanguageMap.put(Locale.ENGLISH, stringMap);
        }
        public static String getString(Context context, int id) {
            Locale locale = getLanguage();
            SparseArray stringMap = (SparseArray) sLanguageMap.get(locale);
            return (String) stringMap.get(id, "");
        }
        private static Locale getLanguage() {
            Locale locale = Locale.getDefault();
            if ((Locale.SIMPLIFIED_CHINESE.equals(locale))
                    || (Locale.TRADITIONAL_CHINESE.equals(locale))) {
                return locale;
            }
            return Locale.ENGLISH;
        }
}
