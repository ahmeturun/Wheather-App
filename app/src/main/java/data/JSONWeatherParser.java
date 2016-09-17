package data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Utils;
import model.Place;
import model.Weather;

/**
 * Created by ahmet on 8/17/2016.
 */
public class JSONWeatherParser {
    public static Weather getWeather(String data) {
        Weather weather = new Weather();
        double[] weeklyTemps = new double[7];
        String[] weeklyIcons = new String[7];
        String[] weeklyDates = new String[7];
        String[] weeklyConditions = new String[7];
        double[] weeklyWinds = new double[7];

        //Creating JsonObject from String data retrieved from Http request then setting all the values on the weather variable

        try {
            JSONObject jsonObject = new JSONObject(data);

            Place place = new Place();

            JSONObject locationObj = Utils.getObject("location", jsonObject);
            JSONObject currentObj = Utils.getObject("current", jsonObject);
            JSONArray forecastdayArray = jsonObject.getJSONObject("forecast").getJSONArray("forecastday");

            place.setLat(Utils.getFloat("lat",locationObj));
            place.setLon(Utils.getFloat("lon",locationObj));

            place.setLastUpdate(Utils.getString("last_updated",currentObj));
            place.setSunrise(forecastdayArray.getJSONObject(0).getJSONObject("astro").getString("sunrise"));
            place.setSunset(forecastdayArray.getJSONObject(0).getJSONObject("astro").getString("sunset"));
            place.setCity(Utils.getString("name",locationObj));
            place.setCountry(Utils.getString("country",locationObj));
            weather.place=place;


            //Set the Weather info comes from JSON returned by Http request
            weather.currentCondition.setWeatherId(forecastdayArray.getJSONObject(0).getInt("date_epoch"));
            weather.currentCondition.setDescription(forecastdayArray.getJSONObject(0).getJSONObject("day").getJSONObject("condition").getString("text"));
            weather.currentCondition.setCondition(forecastdayArray.getJSONObject(0).getJSONObject("day").getJSONObject("condition").getString("text"));
            weather.currentCondition.setIcon(currentObj.getJSONObject("condition").getString("icon"));
            weather.currentCondition.setHumidity(Utils.getInt("humidity",currentObj));
            weather.currentCondition.setPressure(Utils.getInt("pressure_mb",currentObj));
            weather.currentCondition.setMinTemp(forecastdayArray.getJSONObject(0).getJSONObject("day").getInt("mintemp_c"));
            weather.currentCondition.setMacTemp(forecastdayArray.getJSONObject(0).getJSONObject("day").getInt("maxtemp_c"));
            weather.currentCondition.setTemperature(Utils.getDouble("temp_c",currentObj));
            weather.wind.setSpeed(Utils.getFloat("wind_kph",currentObj));
            weather.wind.setDeg(Utils.getFloat("wind_degree",currentObj));
            weather.clouds.setPrecipitation(Utils.getInt("cloud",currentObj));
            for(int i = 0; i<7; i++){
                weeklyTemps[i] = forecastdayArray.getJSONObject(i).getJSONObject("day").getDouble("avgtemp_c");
                weeklyIcons[i] = forecastdayArray.getJSONObject(i).getJSONObject("day").getJSONObject("condition").getString("icon");
                weeklyDates[i] = forecastdayArray.getJSONObject(i).getString("date");
                weeklyWinds[i] = forecastdayArray.getJSONObject(i).getJSONObject("day").getDouble("maxwind_kph");
                weeklyConditions[i] = forecastdayArray.getJSONObject(i).getJSONObject("day").getJSONObject("condition").getString("text");
            }
            weather.setWeeklyTemps(weeklyTemps);
            weather.setWeeklyIcons(weeklyIcons);
            weather.setWeeklyDates(weeklyDates);
            weather.setWeeklyConditions(weeklyConditions);
            weather.setWeeklyWinds(weeklyWinds);

            return weather;


        } catch (JSONException e) {
            Log.v("letscatch: ",e.toString());
            e.printStackTrace();
        }
        return null;
    }
}
