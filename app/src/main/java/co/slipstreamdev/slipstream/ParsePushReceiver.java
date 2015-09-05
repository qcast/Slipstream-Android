package co.slipstreamdev.slipstream;

import android.content.Context;
import android.content.Intent;

import com.parse.ParsePushBroadcastReceiver;

public class ParsePushReceiver extends ParsePushBroadcastReceiver {

    public static final String PARSE_ARTIFACT = "artifact";

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        String url = intent.getExtras().getString(PARSE_ARTIFACT);
        Intent update = new Intent(context, UpdateService.class);
        update.putExtra(PARSE_ARTIFACT, url);
        context.startService(update);
    }
}
