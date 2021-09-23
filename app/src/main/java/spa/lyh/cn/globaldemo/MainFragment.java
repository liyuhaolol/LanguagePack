package spa.lyh.cn.globaldemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.tangyin.mobile.languagedemo.LocalActivity;

import spa.lyh.cn.moudle.TestActivity;
import spa.lyh.cn.peractivity.PermissionActivity;

public class MainFragment extends Fragment implements View.OnClickListener, LocaleReceiver.Message {
    private Button local_click,no_need,moudle,web,storage;
    private ImageView lan_icon;
    public LocaleReceiver receiver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        local_click = view.findViewById(R.id.local_click);
        local_click.setOnClickListener(this);
        no_need = view.findViewById(R.id.no_need);
        no_need.setOnClickListener(this);
        moudle = view.findViewById(R.id.moudle);
        moudle.setOnClickListener(this);
        web = view.findViewById(R.id.web);
        web.setOnClickListener(this);
        storage = view.findViewById(R.id.storage);
        storage.setOnClickListener(this);
        lan_icon = view.findViewById(R.id.local_img);
        receiver = new LocaleReceiver(this);
        LocaleReceiver.register(getActivity(),receiver);
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null){
            LocaleReceiver.unregister(getActivity(),receiver);
        }
    }

    private void loadData(){
        lan_icon.setBackground(ResourcesCompat.getDrawable(LanguageUtils.getResources(getActivity()), R.drawable.flag,null));
        local_click.setText(LanguageUtils.getString(getActivity(),R.string.enter));
        no_need.setText(LanguageUtils.getString(getActivity(),R.string.no_need));
        moudle.setText(LanguageUtils.getString(getActivity(),R.string.moudle));
        web.setText(LanguageUtils.getString(getActivity(),R.string.web));
        storage.setText(LanguageUtils.getString(getActivity(),R.string.per));
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.local_click:
                intent = new Intent(getActivity(), LocalActivity.class);
                startActivity(intent);
                break;
            case R.id.no_need:
                intent = new Intent(getActivity(), NoNeedActivity.class);
                startActivity(intent);
                break;
            case R.id.moudle:
                intent = new Intent(getActivity(), TestActivity.class);
                startActivity(intent);
                break;
            case R.id.web:
                intent = new Intent(getActivity(), WebWeb.class);
                intent.putExtra("url","https://iask.sina.com.cn/b/6324549.html");
                intent.putExtra("title","测试标题");
                startActivity(intent);
                break;
            case R.id.storage:
                MainActivity activity = (MainActivity) getActivity();
                activity.askForPermission(PermissionActivity.REQUIRED_ONLY_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE);
                break;
        }
    }

    @Override
    public void onLanguageChange() {
        loadData();
    }


}
