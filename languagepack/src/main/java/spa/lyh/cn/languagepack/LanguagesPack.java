package spa.lyh.cn.languagepack;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;

import java.util.List;
import java.util.Locale;

import spa.lyh.cn.languagepack.model.LanguageInfo;

public class LanguagesPack {
    /**
     * 初始化国际化框架
     */
    public static void init(Application application) {
        LanguagesChange.register(application);
    }

    /**
     * 在上下文的子类中重写 attachBaseContext 方法（用于更新 Context 的语种）
     */
    public static Context attach(Context context) {
        return LanguagesUtils.updateLanguages(context, getAppLanguage(context));
    }

    /**
     * 获取 App 的语种
     */
    public static Locale getAppLanguage(Context context) {
        return LanguagesSave.getAppLanguage(context);
    }

    /**
     * 将 App 语种设置为系统语种
     */
    public static void setSystemLanguage(Context context) {
        Locale oldLocale = getAppLanguage(context);
        LanguagesSave.clearLanguage(context);
        LanguagesUtils.updateLanguages(context, getSystemLanguage());
        Locale newLocale = getAppLanguage(context);
        if (!isSame(oldLocale,newLocale)){
            sendLanguageBroadcast(context);
        }
    }

    /**
     * 设置 App 的语种
     */
    public static void setAppLanguage(Context context, Locale locale) {
        Locale oldLocale = getAppLanguage(context);
        LanguagesSave.saveAppLanguage(context, locale);
        // 更新当前和全局上下文的语种
        LanguagesUtils.updateLanguages(context, locale);
        if (context != context.getApplicationContext()) {
            LanguagesUtils.updateLanguages(context.getApplicationContext(), locale);
        }
        Locale newLocale = getAppLanguage(context);
        if (!isSame(oldLocale,newLocale)){
            sendLanguageBroadcast(context);
        }
    }

    /**
     * 根据LanguageInfo类动态设置语种
     */
    public static void setLanguage(Context context, LanguageInfo info){
        Locale oldLocale,newLocale;
        if (TextUtils.isEmpty(info.language)){
            //语言为空为系统默认语言
            oldLocale = getAppLanguage(context);
            setSystemLanguage(context);
            newLocale = getAppLanguage(context);
            if (!isSame(oldLocale,newLocale)){
                sendLanguageBroadcast(context);
            }
        }else {
            oldLocale = getAppLanguage(context);
            setAppLanguage(context,new Locale(info.language,info.country));
            newLocale = getAppLanguage(context);
            if (!isSame(oldLocale,newLocale)){
                sendLanguageBroadcast(context);
            }
        }
    }

    /**
     * 获取系统的语种
     */
    public static Locale getSystemLanguage() {
        return LanguagesChange.getSystemLanguage();
    }

    /**
     * 对比两个语言是否是同一个语种（比如：中文的简体和繁体，英语的美式和英式）
     */
    public static boolean equalsLanguage(Locale locale1, Locale locale2) {
        return locale1.getLanguage().equals(locale2.getLanguage());
    }

    /**
     * 对比两个语言是否是同一个地方的（比如：中国大陆用的中文简体，中国台湾用的中文繁体）
     */
    public static boolean equalsCountry(Locale locale1, Locale locale2) {
        return equalsLanguage(locale1, locale2) && locale1.getCountry().equals(locale2.getCountry());
    }
    /**
     * 对比两个语言是否一致
     */
    public static boolean isSame(Locale locale1, Locale locale2){
        if (equalsLanguage(locale1,locale2)){
            //两个语言是一样的，要判断国家地区
            return equalsCountry(locale1,locale2);
        }else {
            return false;
        }
    }

    /**
     * 获取某个语种下的 String
     */
    public static String getLanguageString(Context context, Locale locale, int id) {
        return getLanguageResources(context, locale).getString(id);
    }

    /**
     * 获取当前语言的String
     */
    public static String getString(Context context, int id) {
        return getLanguageString(context,getAppLanguage(context),id);
    }

    /**
     * 获取某个语种下的 Resources 对象
     */
    public static Resources getLanguageResources(Context context, Locale locale) {
        return LanguagesUtils.getLanguageResources(context, locale);
    }

    /**
     * 获取当前语言的 Resources 对象
     */
    public static Resources getResources(Context context) {
        return getLanguageResources(context,getAppLanguage(context));
    }

    /**
     * 设置保存的 SharedPreferences 文件名
     */
    public static void setSharedPreferencesName(String name) {
        LanguagesSave.setSharedPreferencesName(name);
    }


    /**
     * 调用此方法会触发广播回调
     */
    public static void sendLanguageBroadcast(Context context){
        Intent intent=new Intent();
        intent.setAction(context.getPackageName());
        context.sendBroadcast(intent);
    }

    /**
     *通过assets文件夹下的Locales.xml文件获取对应的语言列表
     */
    public static List<LanguageInfo> getLanguageList(Context context){
        return XmlControler.getLanguageList(context);
    }

    /**
     * 生成可以作为系统语言可用的LanguageInfo类
     */
    public static LanguageInfo getSystemLanguageInfo(String content){
        LanguageInfo info = new LanguageInfo();
        info.name = "system";
        info.language = "";
        info.country = "";
        info.content = content;
        return info;
    }

}
