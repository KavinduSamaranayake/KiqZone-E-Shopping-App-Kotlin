package com.saegis.kiqzone;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class OfflineDatabaseAccess extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
