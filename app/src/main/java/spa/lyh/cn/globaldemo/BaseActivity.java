package spa.lyh.cn.globaldemo;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.hjq.language.MultiLanguages;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void attachBaseContext(Context newBase) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(MultiLanguages.attach(newBase));
    }
}
