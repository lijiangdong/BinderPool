package com.example.ljd_pc.binderpool;


import android.os.RemoteException;


/**
 * Created by ljd-PC on 2016/2/21.
 */
public class IRectImpl extends IRect.Stub {
    @Override
    public int area(int length, int width) throws RemoteException {
        return length * width;
    }

    @Override
    public int perimeter(int length, int width) throws RemoteException {
        return length*2 + width*2;
    }
}
