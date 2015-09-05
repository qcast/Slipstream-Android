package co.slipstreamdev.slipstream;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

public class ParsePushReceiver extends ParsePushBroadcastReceiver {

    public static final String TAG = "Slipstream";

    public static final String PARSE_ARTIFACT = "artifact";
    public static final String PARSE_DATA_LOCATION = "com.parse.Data";

    @Override
    protected void onPushReceive(Context context, Intent intent) {

        // Create notification.
        super.onPushReceive(context, intent);

        // Update.
        Bundle extras = intent.getExtras();
        String url;
        try {
            JSONObject pushData = new JSONObject(extras.getString(PARSE_DATA_LOCATION));
            url = pushData.getString(PARSE_ARTIFACT);
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            return;
        }
        Intent update = new Intent(context, UpdateService.class);
        update.putExtra(PARSE_ARTIFACT, url);
        Log.d(TAG, url);
        context.startService(update);

    }
}
