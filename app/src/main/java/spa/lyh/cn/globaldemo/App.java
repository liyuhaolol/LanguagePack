package spa.lyh.cn.globaldemo;

import android.app.Application;
import android.content.Context;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LanguageUtils.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(LanguageUtils.attach(base));
    }
}
