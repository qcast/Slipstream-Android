package co.slipstreamdev.slipstream;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.ParseAnalytics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Slipstream";

    private List<Subscription> channels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_main_toolbar);
        setSupportActionBar(toolbar);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        SharedPreferences preferences = getSharedPreferences(SlipApplication.SHARED_PREFS_NAME, MODE_PRIVATE);
        Set<String> subscribedChannels = preferences.getStringSet(SlipApplication.PREF_SUBSCRIPTIONS, null);

        RecyclerView cardRecyclerView = (RecyclerView) findViewById(R.id.main_card_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        cardRecyclerView.setLayoutManager(layoutManager);

        if (subscribedChannels != null) {
            initializeSubscriptions(subscribedChannels);

            CardAdapter adapter = new CardAdapter(channels, this);
            cardRecyclerView.setAdapter(adapter);
        } else {
            Log.d(TAG, "null subscriptions");
            cardRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeSubscriptions(Set<String> subscribedChannels) {
        channels = new ArrayList<>();
        // TODO Retrofit

        for (String channel : subscribedChannels) {
            Subscription sub = new Subscription(channel, "http://i.imgur.com/DvpvklR.png", "test");
            channels.add(sub);
            Log.d(TAG, "adding channel " + channel);
        }

    }

    public void addNewApp(View view) {
        Intent intent = new Intent(this, NewAppActivity.class);
        startActivity(intent);
    }
}
