package co.slipstreamdev.slipstream;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.parse.ParsePush;

import java.util.HashSet;
import java.util.Set;

public class NewAppActivity extends AppCompatActivity {

    public static final String TAG = "Slipstream";

    private static final String ENTERED_TEXT = "entered_text";
    private static final String TOGGLE_STATE = "toggle_state";

    private boolean toggleState = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_app);

        Toolbar toolbar = (Toolbar) findViewById(R.id.text_toolbar);
        Log.d(TAG, "showing toolbar");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Switch autoUpdateSwitch = (Switch) findViewById(R.id.autotoggle);
        autoUpdateSwitch.setChecked(true);
        autoUpdateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleState = isChecked;
            }
        });

        if (savedInstanceState != null) {
            ((EditText) findViewById(R.id.project_text)).setText(savedInstanceState.getString(ENTERED_TEXT));
            ((Switch) findViewById(R.id.autotoggle)).setChecked(savedInstanceState.getBoolean(TOGGLE_STATE));
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ENTERED_TEXT, ((EditText) findViewById(R.id.project_text)).getText().toString());
        outState.putBoolean(TOGGLE_STATE, toggleState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_app, menu);
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

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void confirmClick(View view) {
        SharedPreferences preferences = getSharedPreferences(SlipApplication.SHARED_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> subscribedChannels = preferences.getStringSet(SlipApplication.PREF_SUBSCRIPTIONS, new HashSet<String>());
        subscribedChannels.add(((EditText) findViewById(R.id.project_text)).getText().toString());
        editor.putStringSet(SlipApplication.PREF_SUBSCRIPTIONS, subscribedChannels);
        ParsePush.subscribeInBackground(((EditText) findViewById(R.id.project_text)).getText().toString());
    }
}
