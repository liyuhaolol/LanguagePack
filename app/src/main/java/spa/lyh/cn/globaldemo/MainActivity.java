package spa.lyh.cn.globaldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hjq.language.MultiLanguages;

import java.util.Locale;

import spa.lyh.cn.globaldemo.local.LocalActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button local_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //loadData();
    }

    private void initView(){
        local_click = findViewById(R.id.local_click);
        local_click.setOnClickListener(this);
    }

    private void loadData(){
        //String content = getString(R.string.enter);
        String content = MultiLanguages.getLanguageString(this,MultiLanguages.getAppLanguage(this),R.string.enter);
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
    protected void onResume() {
        super.onResume();
        Locale locale = MultiLanguages.getAppLanguage(this);
        Log.e("qwer",locale.toLanguageTag());
        loadData();
    }
}