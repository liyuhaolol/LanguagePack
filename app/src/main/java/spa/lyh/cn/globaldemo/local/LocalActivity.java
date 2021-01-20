package spa.lyh.cn.globaldemo.local;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Locale;

import spa.lyh.cn.globaldemo.BaseActivity;
import spa.lyh.cn.globaldemo.R;
import spa.lyh.cn.languagepack.LanguageReceiver;
import spa.lyh.cn.languagepack.LanguagesPack;

public class LocalActivity extends BaseActivity implements View.OnClickListener, LanguageReceiver.Message {
    Button system,zh_cn,en;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
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
        receiver = new LanguageReceiver(this);
        LanguageReceiver.register(this,receiver);
    }

    private void loadData(){
        actionBar.setTitle(LanguagesPack.getString(this,R.string.app_name));
        system.setText(LanguagesPack.getString(this,R.string.follow));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.system:
                LanguagesPack.setSystemLanguage(LocalActivity.this);
                break;
            case R.id.zh_cn:
                LanguagesPack.setAppLanguage(LocalActivity.this, Locale.CHINA);
                break;
            case R.id.en:
                LanguagesPack.setAppLanguage(LocalActivity.this, Locale.ENGLISH);
                break;
        }
    }

    @Override
    public void onLanguageChange() {
        loadData();
    }
}
