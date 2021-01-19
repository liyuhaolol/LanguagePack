package spa.lyh.cn.globaldemo;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.util.Locale;

import spa.lyh.cn.globaldemo.local.LocalActivity;
import spa.lyh.cn.languagepack.LanguageReceiver;
import spa.lyh.cn.languagepack.LanguagesPack;

public class MainActivity extends BaseActivity implements View.OnClickListener, LanguageReceiver.Message {
    private Button local_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();
    }

    private void initView(){
        local_click = findViewById(R.id.local_click);
        local_click.setOnClickListener(this);
        //receiver = new LanguageReceiver(this);
        //LanguageReceiver.register(this,receiver);
    }

    private void loadData(){
        Locale locale = LanguagesPack.getAppLanguage(this);
        Log.e("qwer",locale.toLanguageTag());
        String content = LanguagesPack.getString(this,R.string.enter);
        Log.e("qwer","内容为："+content);
        local_click.setText(content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.local_click:
                Intent intent = new Intent(MainActivity.this, LocalActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onLanguageChange() {
        loadData();
    }
}