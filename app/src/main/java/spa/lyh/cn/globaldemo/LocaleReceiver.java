package spa.lyh.cn.globaldemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;

public class LocaleReceiver extends BroadcastReceiver {
    private LocaleReceiver.Message message;

    public LocaleReceiver(LocaleReceiver.Message message){
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


    public static void register(Context context, LocaleReceiver receiver){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(context.getPackageName()+".global.languageframework");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(receiver, intentFilter,Context.RECEIVER_NOT_EXPORTED);
        }else {
            context.registerReceiver(receiver, intentFilter);
        }
    }

    public static void unregister(Context context,LocaleReceiver receiver){
        if (receiver != null){
            try {
                context.unregisterReceiver(receiver);
            }catch (IllegalArgumentException e){
            }
        }
    }
}
