package co.slipstreamdev.slipstream;

import java.util.List;

/**
 * Created by jcarr on 9/6/2015.
 */
public class SlipstreamApp {
    String icon;
    String displayName;
    List<Build> builds;

    public SlipstreamApp(String icon, String displayName, List<Build> builds) {
        this.icon = icon;
        this.displayName = displayName;
        this.builds = builds;
    }
}

