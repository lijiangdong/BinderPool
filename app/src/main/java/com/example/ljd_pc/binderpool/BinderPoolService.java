package com.example.ljd_pc.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BinderPoolService extends Service {
    private Binder mBinderPool = new BinderPool.BinderPoolImpl();

    public BinderPoolService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinderPool;
    }
}
