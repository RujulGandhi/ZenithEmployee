package app.com.zenith;

import android.app.Application;
import android.support.multidex.MultiDex;

/**
 * Created by archirayan on 21-Apr-17.
 */

public class Zenith extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
