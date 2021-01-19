package spa.lyh.cn.globaldemo.local;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import java.util.Locale;

import spa.lyh.cn.globaldemo.BaseActivity;
import spa.lyh.cn.globaldemo.R;
import spa.lyh.cn.languagepack.LanguagesPack;

public class LocalActivity extends BaseActivity implements View.OnClickListener {
    Button system,zh_cn,en;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        Log.e("qwer","启动页面");
        initView();
        loadData();
    }

    private void initView(){
        system = findViewById(R.id.system);
        system.setOnClickListener(this);
        zh_cn = findViewById(R.id.zh_cn);
        zh_cn.setOnClickListener(this);
        en = findViewById(R.id.en);
        en.setOnClickListener(this);
    }

    private void loadData(){
        system.setText(R.string.follow);
    }


    @Override
    public void onClick(View v) {
        // 是否需要重启
        boolean restart;
        switch (v.getId()){
            case R.id.system:
                restart = LanguagesPack.setSystemLanguage(this);
                break;
            case R.id.zh_cn:
                restart = LanguagesPack.setAppLanguage(this, Locale.CHINA);
                break;
            case R.id.en:
                restart = LanguagesPack.setAppLanguage(this, Locale.ENGLISH);
                break;
            default:
                restart = false;
                break;
        }
        if (restart) {
            Log.e("qwer","需要切换");

        }else {
            Log.e("qwer","语言不需要切换");
        }
    }
}
