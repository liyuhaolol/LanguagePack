package spa.lyh.cn.languagepack;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;


import com.hjq.language.MultiLanguages;


import java.util.List;
import java.util.Locale;

import spa.lyh.cn.languagepack.model.LanguageInfo;

public class LanguagesPack {
    /**
     * 初始化国际化框架
     */
    public static void init(Application application) {
        MultiLanguages.init(application);
    }

    /**
     * 在上下文的子类中重写 attachBaseContext 方法（用于更新 Context 的语种）
     */
    public static Context attach(Context context) {
        return MultiLanguages.attach(context);
    }

    /**
     * 获取 App 的语种
     */
    public static Locale getAppLanguage(Context context) {
        return MultiLanguages.getAppLanguage(context);
    }

    /**
     * 将 App 语种设置为系统语种
     */
    public static boolean setSystemLanguage(Context context) {
        Locale oldLocale = getAppLanguage(context);
        boolean result = MultiLanguages.setSystemLanguage(context);
        Locale newLocale = getAppLanguage(context);
        if (result || !isSame(oldLocale,newLocale)){
            sendLanguageBroadcast(context);
        }
        return result;
    }

    /**
     * 设置 App 的语种
     */
    public static boolean setAppLanguage(Context context, Locale locale) {
        Locale oldLocale = getAppLanguage(context);
        boolean result = MultiLanguages.setAppLanguage(context,locale);
        Locale newLocale = getAppLanguage(context);
        if (result || !isSame(oldLocale,newLocale)){
            sendLanguageBroadcast(context);
        }
        return result;
    }

    public static boolean setLanguage(Context context, LanguageInfo info){
        Locale oldLocale,newLocale;
        boolean result = false;
        if (TextUtils.isEmpty(info.language)){
            //语言为空为系统默认语言
            oldLocale = getAppLanguage(context);
            result = MultiLanguages.setSystemLanguage(context);
            newLocale = getAppLanguage(context);
            if (result || !isSame(oldLocale,newLocale)){
                sendLanguageBroadcast(context);
            }
        }else {
            oldLocale = getAppLanguage(context);
            result = MultiLanguages.setAppLanguage(context,new Locale(info.language,info.country));
            newLocale = getAppLanguage(context);
            if (result || !isSame(oldLocale,newLocale)){
                sendLanguageBroadcast(context);
            }
        }
        return result;
    }

    /**
     * 获取系统的语种
     */
    public static Locale getSystemLanguage() {
        return MultiLanguages.getSystemLanguage();
    }

    /**
     * 对比两个语言是否是同一个语种（比如：中文的简体和繁体，英语的美式和英式）
     */
    public static boolean equalsLanguage(Locale locale1, Locale locale2) {
        return MultiLanguages.equalsLanguage(locale1,locale2);
    }

    /**
     * 对比两个语言是否是同一个地方的（比如：中国大陆用的中文简体，中国台湾用的中文繁体）
     */
    public static boolean equalsCountry(Locale locale1, Locale locale2) {
        return MultiLanguages.equalsCountry(locale1,locale2);
    }
    /**
     * 对比两个语言是否一致
     */
    public static boolean isSame(Locale locale1, Locale locale2){
        if (MultiLanguages.equalsLanguage(locale1,locale2)){
            //两个语言是一样的，要判断国家地区
            return MultiLanguages.equalsCountry(locale1,locale2);
        }else {
            return false;
        }
    }

    /**
     * 获取某个语种下的 String
     */
    public static String getLanguageString(Context context, Locale locale, int id) {
        return MultiLanguages.getLanguageString(context,locale,id);
    }

    /**
     * 获取当前语言的String
     */
    public static String getString(Context context, int id) {
        return MultiLanguages.getLanguageString(context,MultiLanguages.getAppLanguage(context),id);
    }

    /**
     * 获取某个语种下的 Resources 对象
     */
    public static Resources getLanguageResources(Context context, Locale locale) {
        return MultiLanguages.getLanguageResources(context,locale);
    }

    /**
     * 获取当前语言的 Resources 对象
     */
    public static Resources getResources(Context context) {
        return MultiLanguages.getLanguageResources(context,MultiLanguages.getAppLanguage(context));
    }

    /**
     * 设置保存的 SharedPreferences 文件名
     */
    public static void setSharedPreferencesName(String name) {
        MultiLanguages.setSharedPreferencesName(name);
    }


    /**
     * 调用此方法会触发广播回调
     */
    public static void sendLanguageBroadcast(Context context){
        Intent intent=new Intent();
        intent.setAction(context.getPackageName());
        context.sendBroadcast(intent);
    }

    public static List<LanguageInfo> getLanguageList(Context context){
        return XmlControler.getLanguageList(context);
    }

    public static LanguageInfo getSystemLanguageInfo(String content){
        LanguageInfo info = new LanguageInfo();
        info.name = "system";
        info.language = "";
        info.country = "";
        info.content = content;
        return info;
    }

}
