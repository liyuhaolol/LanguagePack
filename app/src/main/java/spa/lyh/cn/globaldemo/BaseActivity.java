package spa.lyh.cn.globaldemo;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import spa.lyh.cn.languagepack.LanguagesPack;


public class BaseActivity extends AppCompatActivity {


    @Override
    protected void attachBaseContext(Context newBase) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(LanguagesPack.attach(newBase));
    }
}
