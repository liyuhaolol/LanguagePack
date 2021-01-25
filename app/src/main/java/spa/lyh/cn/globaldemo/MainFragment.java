package spa.lyh.cn.globaldemo;

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

import spa.lyh.cn.languagepack.LanguageReceiver;
import spa.lyh.cn.languagepack.LanguagesPack;

public class MainFragment extends Fragment implements View.OnClickListener, LanguageReceiver.Message {
    private Button local_click,no_need;
    private ImageView lan_icon;
    public LanguageReceiver receiver;

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
        lan_icon = view.findViewById(R.id.local_img);
        receiver = new LanguageReceiver(this);
        LanguageReceiver.register(getActivity(),receiver);
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null){
            LanguageReceiver.unregister(getActivity(),receiver);
        }
    }

    private void loadData(){
        lan_icon.setBackground(ResourcesCompat.getDrawable(LanguagesPack.getResources(getActivity()), R.drawable.flag,null));
        local_click.setText(LanguagesPack.getString(getActivity(),R.string.enter));
        no_need.setText(LanguagesPack.getString(getActivity(),R.string.no_need));
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
        }
    }

    @Override
    public void onLanguageChange() {
        loadData();
    }
}
