package spa.lyh.cn.globaldemo;

import android.app.Application;
import android.content.Context;

import com.hjq.language.MultiLanguages;

public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(MultiLanguages.attach(base));
    }
}
