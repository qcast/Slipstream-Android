package co.slipstreamdev.slipstream;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.parse.Parse;

public class UpdateService extends Service {

    String url;
    private boolean running;
    private static Thread task;

    @Override
    public void onCreate() {
        running = false;
        task = new Thread(new Runnable() {
            @Override
            public void run() {

                stopSelf();
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        url = intent.getStringExtra(ParsePushReceiver.PARSE_ARTIFACT);
        if (!running) {
            running = true;
            task.start();
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
}
