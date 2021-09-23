package spa.lyh.cn.globaldemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 无需进行复杂集成的演示，只需要实现attachBaseContext即可
 */
public class NoNeedActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_need);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(LanguageUtils.attach(newBase));
    }
}
