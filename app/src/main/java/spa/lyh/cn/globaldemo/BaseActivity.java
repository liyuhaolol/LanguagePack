package spa.lyh.cn.globaldemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import spa.lyh.cn.peractivity.PermissionActivity;


public class BaseActivity extends PermissionActivity {
    public LocaleReceiver receiver;//这里只是探讨组件化解耦，请使用LanguageReceiver
    public ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(LanguageUtils.attach(newBase));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null){
            //Log.e("qwer","注销广播");
            LocaleReceiver.unregister(this,receiver);
        }

    }
}
