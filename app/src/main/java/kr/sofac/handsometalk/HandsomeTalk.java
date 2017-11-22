package kr.sofac.handsometalk;

import android.app.Application;
import android.content.pm.ActivityInfo;
import android.support.multidex.MultiDex;
import android.util.Log;

import kr.sofac.handsometalk.util.FakeCrashLibrary;
import timber.log.Timber;

/**
 * Created by Maxim on 02.11.2017.
 */

public class HandsomeTalk extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }

    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Timber.Tree {

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }

}
