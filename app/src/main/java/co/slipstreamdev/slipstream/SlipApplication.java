package co.slipstreamdev.slipstream;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;

public class SlipApplication extends Application {

    public static final String PARSE_CHANNEL_NAME = "android-demo";

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, BuildConfig.PARSE_APPLICATION_ID, BuildConfig.PARSE_CLIENT_KEY);
        ParseUser.enableAutomaticUser();
        ParsePush.subscribeInBackground(PARSE_CHANNEL_NAME);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
