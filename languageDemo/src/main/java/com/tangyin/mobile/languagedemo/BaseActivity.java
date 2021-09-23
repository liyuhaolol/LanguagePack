package com.tangyin.mobile.languagedemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import spa.lyh.cn.languagepack.LanguageReceiver;
import spa.lyh.cn.languagepack.LanguagesPack;


public class BaseActivity extends AppCompatActivity {
    public LanguageReceiver receiver;//这里只是探讨组件化解耦，请使用LanguageReceiver
    public ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(LanguagesPack.attach(newBase));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null){
            //Log.e("qwer","注销广播");
            LanguageReceiver.unregister(this,receiver);
        }

    }
}
