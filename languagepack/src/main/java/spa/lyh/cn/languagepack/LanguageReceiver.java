package spa.lyh.cn.languagepack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;

public class LanguageReceiver extends BroadcastReceiver {
    private Message message;
    public static final String LAN_ACTION = ".global.languageframework";

    public LanguageReceiver(Message message){
        this.message = message;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (message != null){
            message.onLanguageChange();
        }
    }

    public interface Message{
        void onLanguageChange();
    }

    public static void register(Context context,LanguageReceiver receiver){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(context.getPackageName()+LAN_ACTION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(receiver, intentFilter,Context.RECEIVER_NOT_EXPORTED);
        }else {
            context.registerReceiver(receiver, intentFilter);
        }
    }

    public static void unregister(Context context,LanguageReceiver receiver){
        if (receiver != null){
            try {
                context.unregisterReceiver(receiver);
            }catch (IllegalArgumentException ignored){
            }
        }
    }
}
