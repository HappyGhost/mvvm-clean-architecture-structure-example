package com.myapp.mvvmexample.core.application

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.android.example.github.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class ArticleApplication : MultiDexApplication(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}