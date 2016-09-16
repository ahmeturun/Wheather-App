package data;

import android.content.Context;
import android.test.com.theweatherapp.MainActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Util.Utils;

/**
 * Created by ahmet on 8/17/2016.
 */
public class WeatherHttpClient {
    Context context;
    public WeatherHttpClient(Context context) {
        this.context = context;
    }

    public String getWeatherData(String place){

        final String[] getJsonData = new String[1];
        JsonObjectRequest request = new JsonObjectRequest(Utils.BASE_URL1 + place + Utils.BASE_URL2, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getJsonData[0] = response.toString();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("json_retrieving-problem",error.getMessage());
            }
        });

        SingletonClassVolley.getInstance(context).adToRequestQueue(request);
        Log.v("json-retrived: ","nothing"+getJsonData[0]);

        return getJsonData[0];

//        HttpURLConnection connection = null;
//        InputStream inputStream = null;
//
//        try {
//            connection = (HttpURLConnection) (new URL(Utils.BASE_URL1 + place + Utils.BASE_URL2)).openConnection();
//            connection.setRequestMethod("GET");
//            connection.setDoInput(true);
//            connection.connect();
//
//            //Read the response
//            StringBuffer stringBuffer = new StringBuffer();
//            inputStream = connection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String line = null;
//
//            while ((line = bufferedReader.readLine())!= null){
//                stringBuffer.append(line + "\r\n");
//            }
//
//            inputStream.close();
//            connection.disconnect();
//
//            return  stringBuffer.toString();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
    }
}
