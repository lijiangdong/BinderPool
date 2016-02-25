package com.example.ljd_pc.binderpool;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BinderPoolActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.add_btn)
    Button addBtn;

    @Bind(R.id.sub_btn)
    Button subBtn;

    @Bind(R.id.area_btn)
    Button areaBtn;

    @Bind(R.id.per_btn)
    Button perBtn;
    private final String TAG = "BinderPoolActivity";

    private BinderPool mBinderPool;
    private ICalculate mCalculate;
    private IRect mRect;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            addBtn.setOnClickListener(BinderPoolActivity.this);
            subBtn.setOnClickListener(BinderPoolActivity.this);
            areaBtn.setOnClickListener(BinderPoolActivity.this);
            perBtn.setOnClickListener(BinderPoolActivity.this);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);
        ButterKnife.bind(this);
        getBinderPool();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void getBinderPool(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mBinderPool = BinderPool.getInstance(BinderPoolActivity.this);
                mHandler.obtainMessage().sendToTarget();
            }
        }).start();
    }

    @Override
    public void onClick(final View v) {
        try {
            switch (v.getId()){
                case R.id.add_btn:
                    mCalculate = ICalculateImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_CALCULATE));
                    Log.e(TAG,String.valueOf(mCalculate.add(3,2)));
                    Toast.makeText(BinderPoolActivity.this,String.valueOf(mCalculate.add(3,2)),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.sub_btn:
                    mCalculate = ICalculateImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_CALCULATE));
                    Log.e(TAG,String.valueOf(mCalculate.sub(3,2)));
                    Toast.makeText(BinderPoolActivity.this,String.valueOf(mCalculate.sub(3,2)),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.area_btn:
                    mRect = IRectImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_RECT));
                    Log.e(TAG,String.valueOf(mRect.area(3,2)));
                    Toast.makeText(BinderPoolActivity.this,String.valueOf(mRect.area(3,2)),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.per_btn:
                    mRect = IRectImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_RECT));
                    Log.e(TAG,String.valueOf(mRect.perimeter(3,2)));
                    Toast.makeText(BinderPoolActivity.this,String.valueOf(mRect.perimeter(3,2)),Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
