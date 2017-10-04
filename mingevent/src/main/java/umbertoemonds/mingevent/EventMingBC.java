package umbertoemonds.mingevent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by umbertoemonds on 04/10/2017.
 */

public class EventMingBC extends BroadcastReceiver {

    String identifier;

    public EventMingBC(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null)
            EventMing.onReceive(identifier, intent.getBundleExtra("data"));
        else
            EventMing.onReceive(identifier, null);

    }

}
