package co.slipstreamdev.slipstream;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.PushService;

public class SlipApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, BuildConfig.PARSE_APPLICATION_ID, BuildConfig.PARSE_CLIENT_KEY);
        ParseUser.enableAutomaticUser();
        ParsePush.subscribeInBackground("android-demo");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
