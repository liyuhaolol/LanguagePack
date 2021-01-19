package spa.lyh.cn.globaldemo;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import spa.lyh.cn.languagepack.LanguageReceiver;
import spa.lyh.cn.languagepack.LanguagesPack;


public class BaseActivity extends AppCompatActivity {
    public LanguageReceiver receiver;

    @Override
    protected void attachBaseContext(Context newBase) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(LanguagesPack.attach(newBase));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null){
            Log.e("qwer","注销广播");
            LanguageReceiver.unregister(this,receiver);
        }

    }
}
