package android.test.com.theweatherapp;

import android.content.Context;
import android.util.Log;

import java.text.DecimalFormat;

import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Weather;

/**
 * Created by ahmet on 8/22/2016.
 */
//Setting function for getting weather information from the web
public class WeatherTask {
    Weather weather ;
    Context context;
    String place;

    public static double[] daily_temp;
    public static String[] daily_condition;
    public static double[] daily_wind;

    public WeatherTask(Context context,String place) {
        this.context = context;
        this.place = place;
    }

    public void ParseJsontoMainActivity() {
        new WeatherHttpClient(context).getWeatherData(place, new WeatherHttpClient.VolleyCallback() {
            @Override
            public void onSuccess(String string) {
                weather = JSONWeatherParser.getWeather(string);

                if(weather!=null) {

                    daily_temp = weather.getWeeklyTemps();
                    daily_condition = weather.getWeeklyConditions();
                    daily_wind = weather.getWeeklyWinds();

                    String updateDate = convertStringToDate.convertDate(weather.place.getLastUpdate()).toLocaleString();

                    DecimalFormat decimalFormat = new DecimalFormat("#.#");
                    String tempFormat = decimalFormat.format(weather.currentCondition.getTemperature());

                    FirstFragment.cityName.setText(weather.place.getCity() + "," + weather.place.getCountry());
                    FirstFragment.temp.setText("" + tempFormat + "°C");
                    FirstFragment.humidity.setText("Humidity: " + weather.currentCondition.getHumidity() + " %");
                    FirstFragment.pressure.setText("Pressure: " + weather.currentCondition.getPressure() + " hPa");
                    FirstFragment.wind.setText("Wind: " + weather.wind.getSpeed() + " mps");
                    FirstFragment.sunrise.setText("Sunrise: " +  weather.place.getSunrise());
                    FirstFragment.sunset.setText("Sunset: " + weather.place.getSunset());
                    FirstFragment.updated.setText("Last Updated: " + updateDate);
                    FirstFragment.description.setText("Condition: " + weather.currentCondition.getCondition() + "");
                    FirstFragment.textView0.setText(convertStringToDate.convertDay(weather.weeklyDates[0]));
                    FirstFragment.textView1.setText(convertStringToDate.convertDay(weather.weeklyDates[1]));
                    FirstFragment.textView2.setText(convertStringToDate.convertDay(weather.weeklyDates[2]));
                    FirstFragment.textView3.setText(convertStringToDate.convertDay(weather.weeklyDates[3]));
                    FirstFragment.textView4.setText(convertStringToDate.convertDay(weather.weeklyDates[4]));
                    FirstFragment.textView5.setText(convertStringToDate.convertDay(weather.weeklyDates[5]));
                    FirstFragment.textView6.setText(convertStringToDate.convertDay(weather.weeklyDates[6]));

                    Log.v("weathertask_fragment: ",FirstFragment.cityName.getText().toString());

                    new DownloadImageAsyncTask().execute(weather.currentCondition.getIcon(),weather.weeklyIcons[1],weather.weeklyIcons[2],weather.weeklyIcons[3],weather.weeklyIcons[4],
                            weather.weeklyIcons[5], weather.weeklyIcons[6]);

                }
                else {

                }
            }
        });
    }
}
