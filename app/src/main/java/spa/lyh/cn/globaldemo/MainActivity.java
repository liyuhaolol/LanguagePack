package spa.lyh.cn.globaldemo;


import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends BaseActivity implements LocaleReceiver.Message {
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
        receiver = new LocaleReceiver(this);
        LocaleReceiver.register(this,receiver);
    }

    private void loadData(){
        actionBar.setTitle(LanguageUtils.getString(this,R.string.app_name));
    }



    @Override
    public void onLanguageChange() {
        loadData();
    }

}