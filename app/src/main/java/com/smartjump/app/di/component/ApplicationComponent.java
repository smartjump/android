package com.smartjump.app.di.component;

import com.smartjump.app.activity.SmartJumpActivity;
import com.smartjump.app.di.LifeScope;
import com.smartjump.app.di.module.ApplicationModule;
import com.smartjump.domain.MainThread;
import com.smartjump.domain.ThreadExecutor;

import dagger.Component;

/**
 *
 */
@LifeScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SmartJumpActivity activity);

    MainThread mainThread();

    ThreadExecutor threadExecutor();
}
