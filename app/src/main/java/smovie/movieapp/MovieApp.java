package smovie.movieapp;

import android.app.Application;

/**
 * Created by sniper on 12/6/16.
 */

public class MovieApp extends Application {

    private static MovieApp instance = null;
    public static boolean isOnline = true;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static MovieApp get() {
        if (instance == null) {
            throw new IllegalStateException("MovieApp instance not set before an attempt to get it was made.");
        }
        return instance;
    }
}
