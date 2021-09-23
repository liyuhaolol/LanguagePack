package spa.lyh.cn.globaldemo;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import java.lang.reflect.Method;

public class LanguageUtils {
    private final static String className = "spa.lyh.cn.languagepack.LanguagesPack";

    /**
     * 检查分享模块是否启动
     * @return
     */
    public static boolean isActivited(){
        try{
            Class.forName(className);
            return true;
        }catch (Exception ignored){
            return false;
        }
    }

    public static void init(Application application) {
        try{
            Class clazz = Class.forName(className);
            Method method = clazz.getMethod("init",Application.class);
            method.invoke(clazz,application);
        }catch (Exception ignored){
        }
    }

    public static Context attach(Context context) {
        Object obj;
        try{
            Class clazz = Class.forName(className);
            Method method = clazz.getMethod("attach",Context.class);
            obj = method.invoke(clazz,context);
            return (Context) obj;
        }catch (Exception ignored){
            return context;
        }
    }

    public static String getString(Context context, int id){
        Object obj;
        try{
            Class clazz = Class.forName(className);
            Method method = clazz.getMethod("getString",Context.class,int.class);
            obj = method.invoke(clazz,context,id);
            return (String) obj;
        }catch (Exception ignored){
            return context.getString(id);
        }
    }

    public static Resources getResources(Context context){
        Object obj;
        try{
            Class clazz = Class.forName(className);
            Method method = clazz.getMethod("getResources",Context.class);
            obj = method.invoke(clazz,context);
            return (Resources) obj;
        }catch (Exception ignored){
            return context.getResources();
        }
    }
}
