package umbertoemonds.mingevent;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by umbertoemonds on 04/10/2017.
 */

public class EventMing {

    static OnNotificationReceived onNotificationReceived;

    public static void setOnNotificationReceived(OnNotificationReceived onNotificationReceived) {
        EventMing.onNotificationReceived = onNotificationReceived;
    }

    public interface OnNotificationReceived {
        void onReceived(String identifier, @Nullable Bundle data);
    }

    public static void sendNotification(@NonNull Context context, @NonNull String identifier, @NonNull Bundle data) {
        Intent intent = new Intent(identifier);
        intent.putExtra("data", data);
        context.sendBroadcast(intent);
    }

    public static void sendNotification(@NonNull Context context, @NonNull String identifier) {
        Intent intent = new Intent(identifier);
        context.sendBroadcast(intent);
    }

    public static void subscribeNotifications(@NonNull Context context, @NonNull String identifier) {
        context.registerReceiver(new EventMingBC(identifier), new IntentFilter(identifier));
    }

    public static void onReceive(String identifier, Bundle data) {
        onNotificationReceived.onReceived(identifier, data);
    }

}
