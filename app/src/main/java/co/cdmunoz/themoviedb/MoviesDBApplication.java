package co.cdmunoz.themoviedb;

import android.app.Application;

public class MoviesDBApplication extends Application {

    public static MoviesDBApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
