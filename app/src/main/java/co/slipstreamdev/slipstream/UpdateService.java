package co.slipstreamdev.slipstream;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class UpdateService extends Service {

    String url;
    private boolean running;
    private static GetAppUpdateTask task;

    public interface Listener {
        public void onUpdateDownloaded(String path);
    }

    @Override
    public void onCreate() {
        running = false;
        task = new GetAppUpdateTask(new Listener() {
            @Override
            public void onUpdateDownloaded(String path) {
                installUpdate(path);
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        url = intent.getStringExtra(ParsePushReceiver.PARSE_ARTIFACT);
        if (!running) {
            running = true;
            task.execute(url);
        }
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        running = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void installUpdate(String path) {
        try
        {
            Runtime.getRuntime().exec(new String[] {"su", "-c", "pm install -r " + path});
        }
        catch (IOException e)
        {
            Log.e("Slipstream", e.toString());
        }

    }
}