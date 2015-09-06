package co.slipstreamdev.slipstream;

import android.app.Application;
import android.content.SharedPreferences;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;

import java.util.Set;

public class SlipApplication extends Application {

    public static final String SHARED_PREFS_NAME = "slipstream";
    public static final String PREF_SUBSCRIPTIONS = "subscriptions";

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, BuildConfig.PARSE_APPLICATION_ID, BuildConfig.PARSE_CLIENT_KEY);
        ParseUser.enableAutomaticUser();

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        Set<String> subscribedChannels = preferences.getStringSet(PREF_SUBSCRIPTIONS, null);
        if (subscribedChannels != null) {
            for (String subscription : subscribedChannels) {
                // TODO Way to check if these are valid?
            }
        }
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
