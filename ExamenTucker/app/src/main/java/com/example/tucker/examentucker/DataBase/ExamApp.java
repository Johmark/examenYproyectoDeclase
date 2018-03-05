package com.example.tucker.examentucker.DataBase;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by John on 3/2/2018.
 */

public class ExamApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
