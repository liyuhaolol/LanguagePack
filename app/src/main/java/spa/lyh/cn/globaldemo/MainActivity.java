package spa.lyh.cn.globaldemo;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.Locale;

import spa.lyh.cn.globaldemo.local.LocalActivity;
import spa.lyh.cn.languagepack.LanguageReceiver;
import spa.lyh.cn.languagepack.LanguagesPack;

public class MainActivity extends BaseActivity implements View.OnClickListener, LanguageReceiver.Message {
    private Button local_click;
    private ImageView lan_icon;

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
        receiver = new LanguageReceiver(this);
        lan_icon = findViewById(R.id.local_img);
        LanguageReceiver.register(this,receiver);
    }

    private void loadData(){
        actionBar.setTitle(LanguagesPack.getString(this,R.string.app_name));
        lan_icon.setBackground(ResourcesCompat.getDrawable(LanguagesPack.getResources(this), R.drawable.lan_icon,null));
        local_click.setText(LanguagesPack.getString(this,R.string.enter));
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