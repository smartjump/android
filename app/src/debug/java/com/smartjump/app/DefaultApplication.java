package com.smartjump.app;

import android.os.StrictMode;

import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 *
 */
public class DefaultApplication extends SmartJumpApplication {

    @Override
    void initialize() {
        Timber.plant(new Timber.DebugTree());

        // Detect all kind of problems and log it
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        // Leak canary stuff
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}