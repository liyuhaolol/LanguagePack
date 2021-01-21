package spa.lyh.cn.globaldemo.local;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import spa.lyh.cn.globaldemo.BaseActivity;
import spa.lyh.cn.globaldemo.DemoAdapter;
import spa.lyh.cn.globaldemo.R;
import spa.lyh.cn.languagepack.LanguageReceiver;
import spa.lyh.cn.languagepack.LanguagesPack;
import spa.lyh.cn.languagepack.model.LanguageInfo;

public class LocalActivity extends BaseActivity implements LanguageReceiver.Message {
    private RecyclerView recy;
    private DemoAdapter adapter;
    private List<LanguageInfo> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        initView();
        loadData();
    }

    private void initView(){
        recy = findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new DemoAdapter(this,list);
        recy.setAdapter(adapter);
        receiver = new LanguageReceiver(this);
        LanguageReceiver.register(this,receiver);
        LanguagesPack.getLanguageList(this);
        insertData();
        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LanguagesPack.setLanguage(LocalActivity.this,list.get(position));
            }
        });
    }

    private void loadData(){
        actionBar.setTitle(LanguagesPack.getString(this,R.string.app_name));
        list.get(0).content = LanguagesPack.getString(this,R.string.follow);
        adapter.notifyDataSetChanged();
    }

    private void insertData(){
        list.addAll(LanguagesPack.getLanguageList(this));
        list.add(0,LanguagesPack.getSystemLanguageInfo(getString(R.string.follow)));

    }

    @Override
    public void onLanguageChange() {
        loadData();
    }
}
