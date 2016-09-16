package data;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ahmet on 9/16/2016.
 */
public class SingletonClassVolley {
    private static  SingletonClassVolley mInstance;
    private RequestQueue mRequestQueue;
    private static Context appContext;

    public SingletonClassVolley(Context context) {
        appContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized SingletonClassVolley getInstance(Context context){
        if(mInstance == null){
            mInstance = new SingletonClassVolley(context);
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if(mRequestQueue == null){
            //if the mRequestQueue is null then we will assign a
            // value to it with Volley.newRequestQueue and in that
            // method we will use getApplicationContext to prevent
            // leakage on any Activity or BroadcastReceiver
            // if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(appContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void adToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
