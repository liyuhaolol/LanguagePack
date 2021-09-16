package spa.lyh.cn.languagepack;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

import java.util.Locale;


final class LanguagesUtils {

    /**
     * 判断判断两个语种是否相同
     */
    static boolean equalsLanguages(Locale previousLocale, Locale currentLocale) {
        return previousLocale.equals(currentLocale);
    }

    /**
     * 更新当前 App 的语种
     */
    static Context updateLanguages(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration config = new Configuration(resources.getConfiguration());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LocaleList localeList = new LocaleList(locale);
                LocaleList.setDefault(localeList);
                config.setLocales(localeList);
            } else {
                config.setLocale(locale);
            }
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            resources.updateConfiguration(config, resources.getDisplayMetrics());
        }
        Locale.setDefault(locale);
        return context;
    }

    /**
     * 获取某个语种下的 Resources 对象
     */
    static Resources getLanguageResources(Context context, Locale locale) {
        Configuration config = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LocaleList localeList = new LocaleList(locale);
                LocaleList.setDefault(localeList);
                config.setLocales(localeList);
            } else {
                config.setLocale(locale);
            }
            return context.createConfigurationContext(config).getResources();
        } else {
            config.locale = locale;
            return new Resources(context.getAssets(), context.getResources().getDisplayMetrics(), config);
        }
    }
}