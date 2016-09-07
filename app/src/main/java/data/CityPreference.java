package data;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by ahmet on 8/18/2016.
 */
public class CityPreference {
    SharedPreferences preferences;

    public  CityPreference(Activity activity){
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public  String getCity(){
        return  preferences.getString("city", "Ankara,TR");
    }

    public void setCity(String city){
        preferences.edit().putString("city",city).commit();
    }
}
