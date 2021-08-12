package spa.lyh.cn.globaldemo;


import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import spa.lyh.cn.languagepack.LanguageReceiver;
import spa.lyh.cn.languagepack.LanguagesPack;

public class MainActivity extends BaseActivity implements LanguageReceiver.Message {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private MainFragment main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();
    }

    private void initView(){
        fragmentManager = this.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        main = new MainFragment();
        transaction.add(R.id.fl_main, main);
        transaction.commit();
        receiver = new LanguageReceiver(this);
        LanguageReceiver.register(this,receiver);
    }

    private void loadData(){
        actionBar.setTitle(LanguagesPack.getString(this,R.string.app_name));
    }



    @Override
    public void onLanguageChange() {
        loadData();
    }

}