package co.slipstreamdev.slipstream;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class UpdateService extends Service {

    String url;
    private static GetAppUpdateTask task;

    public interface Listener {
        void onUpdateDownloaded(String path);
    }

    @Override
    public void onCreate() {
        Log.d("Slipstream", "starting service");
        task = new GetAppUpdateTask(new Listener() {
            @Override
            public void onUpdateDownloaded(String path) {
                Log.d("Slipstream", "installing in listener");
                installUpdate(path);
            }
        }, UpdateService.this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        url = intent.getStringExtra(ParsePushReceiver.PARSE_ARTIFACT);
        Log.d("Slipstream", "onstart " + url);
        task.execute(url);
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void installUpdate(String path) {
        try
        {
            Log.d("Slipstream", "su command");
            Runtime.getRuntime().exec(new String[] {"su", "-c", "pm install -r " + path});
        }
        catch (IOException e)
        {
            Log.e("Slipstream", e.toString());
        }

    }
}