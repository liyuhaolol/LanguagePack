package spa.lyh.cn.globaldemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import spa.lyh.cn.languagepack.model.LanguageInfo;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.MyHolder> {
    private Context context;
    private List<LanguageInfo> list;
    private AdapterView.OnItemClickListener listener;

    public DemoAdapter(Context context,List<LanguageInfo> list){
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.btn.setText(list.get(position).content);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onItemClick(null,holder.itemView,position,holder.btn.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        Button btn;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        this.listener = listener;
    }
}
