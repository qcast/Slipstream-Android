package co.slipstreamdev.slipstream;

import android.content.Context;
import android.util.Log;

import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;

public class GetAppUpdateTask {

    public static final String TAG = "Slipstream";

    private static final String DIRECTORY = "/sdcard/Slipstream/";
    private static final String NAME = "update.apk";

    private final UpdateService.Listener mListener;
    private Context mContext;

    private Future<File> downloading;

    public GetAppUpdateTask(UpdateService.Listener listener, Context context) {
        mListener = listener;
        mContext = context;
    }

    public void execute(String url) {

        downloading = Ion.with(mContext)
                .load(url)
                .write(new File(DIRECTORY + NAME))
                .setCallback(new FutureCallback<File>() {
                    @Override
                    public void onCompleted(Exception e, File result) {
                        resetDownload();
                        if (e != null) {
                            Log.e(TAG, e.toString());
                            //return;
                        }
                        mListener.onUpdateDownloaded(DIRECTORY + NAME);
                    }
                });
    }

    private void resetDownload() {
        downloading.cancel();
        downloading = null;
    }
}

