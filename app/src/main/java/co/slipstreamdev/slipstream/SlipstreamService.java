package co.slipstreamdev.slipstream;

import java.util.List;

import retrofit.Call;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by jcarr on 9/6/2015.
 */
public interface SlipstreamService {
    //@GET("/{app}/")
    //void getApp(@Query("") String )

    public class App {
        String icon;
        String displayName;
        List<Build> builds;
    }

    public class Build {
        int number;
    }

}
