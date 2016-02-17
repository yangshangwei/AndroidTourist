package com.turing.base.activity.service.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLService extends Service {

    public class MyServiceImpl extends IMyService.Stub {

        @Override
        public String getValue() throws RemoteException {
            return "AIDL.....";
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyServiceImpl();
    }
}
