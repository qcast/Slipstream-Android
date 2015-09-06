package co.slipstreamdev.slipstream;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by jcarr on 9/6/2015.
 */
public class Subscription {

    public static final String TAG = "Slipstream";

    String name;
    String imageURL;
    String latestBuildInfo;

    Subscription(String name, String imageURL, String latestBuildInfo) {
        this.name = name;
        this.imageURL = imageURL;
        this.latestBuildInfo = latestBuildInfo;
    }
}

