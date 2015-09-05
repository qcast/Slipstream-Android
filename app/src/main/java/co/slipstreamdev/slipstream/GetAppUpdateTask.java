package co.slipstreamdev.slipstream;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetAppUpdateTask extends AsyncTask<String, Void, String> {

    private static final String DIRECTORY = "/sdcard/Slipstream";
    private static final String NAME = "update.apk";

    private final UpdateService.Listener mListener;

    public GetAppUpdateTask(UpdateService.Listener listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(String... arg0) {
        try {
            URL url = new URL(arg0[0]);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setDoOutput(true);
            httpConnection.connect();

            File updateFile = new File(DIRECTORY);
            updateFile.mkdirs();
            File outputFile = new File(updateFile, NAME);
            if (outputFile.exists())
                outputFile.delete();

            FileOutputStream stream = new FileOutputStream(outputFile);

            InputStream input = httpConnection.getInputStream();

            byte[] buffer = new byte[1024];
            int i;
            while ((i = input.read(buffer)) != -1) {
                stream.write(buffer, 0, i);
            }
            stream.close();
            input.close();

        } catch (Exception e) {
            Log.e("Slipstream", "Update error " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        mListener.onUpdateDownloaded(DIRECTORY + NAME);
    }
}

