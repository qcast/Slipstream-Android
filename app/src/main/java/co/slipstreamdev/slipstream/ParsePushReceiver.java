package co.slipstreamdev.slipstream;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

public class ParsePushReceiver extends ParsePushBroadcastReceiver {

    public static final String PARSE_ARTIFACT = "artifact";

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String url;
        try {
            JSONObject pushData = new JSONObject(extras.getString("com.parse.Data"));
            url = pushData.getString(PARSE_ARTIFACT);
        } catch (JSONException e) {
            Log.e("Slipstream", e.toString());
            return;
        }
        Intent update = new Intent(context, UpdateService.class);
        update.putExtra(PARSE_ARTIFACT, url);
        Log.d("Slipstream", url);
        context.startService(update);
    }
}
