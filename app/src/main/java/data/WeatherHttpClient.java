package data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import Util.Utils;

/**
 * Created by ahmet on 8/17/2016.
 */
public class WeatherHttpClient {
    Context context;
    public WeatherHttpClient(Context context) {
        this.context = context;
    }

    public void getWeatherData(String place,final VolleyCallback callback){

        try {
            JsonObjectRequest request = new JsonObjectRequest(Utils.BASE_URL1 + place + Utils.BASE_URL2, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.v("returned_JSON: ", response.toString());
                    callback.onSuccess(response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("json_retrieving-problem", error.getMessage());
                    Toast.makeText(context, "Data is Unavailable.(Volley Request)", Toast.LENGTH_SHORT).show();
                }
            });
            SingletonClassVolley.getInstance(context).adToRequestQueue(request);
        }
        catch (Exception e){
            Log.v("exception: ",e.toString());
            Toast.makeText(context, "Data is Unavailable.(Http Client)", Toast.LENGTH_SHORT).show();
        }
    }
    public interface VolleyCallback{
        void onSuccess(String string);
    }
}
