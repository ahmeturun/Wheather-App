package Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ahmet on 8/17/2016.
 */
public class Utils {

    public static final String BASE_URL1 = "http://api.apixu.com/v1/forecast.json?key=6d0b9f41b92449b5a75161828162008&q=";
    public static final String BASE_URL2 = "&days=7";
    //public static final String ICON_URL = "http://openweathermap.org/img/w/";

    public static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException {
        JSONObject jObj = jsonObject.getJSONObject(tagName);
        return jObj;
    }

    public static String getString(String tagName, JSONObject jsonObject ) throws JSONException {
        return  jsonObject.getString(tagName);
    }

    public static float getFloat(String tagName, JSONObject jsonObject) throws JSONException{
        return (float)jsonObject.getDouble(tagName);
    }

    public static double getDouble (String tagName, JSONObject jsonObject) throws JSONException{
        return (float)jsonObject.getDouble(tagName);
    }

    public static int getInt(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(tagName);
    }
}
