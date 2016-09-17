package android.test.com.theweatherapp;

import android.content.Context;

import java.text.DecimalFormat;

import data.JSONWeatherParser;
import data.VolleyRequest;
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
        new VolleyRequest(context).getWeatherData(place, new VolleyRequest.VolleyCallback() {
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

                    MainActivity.cityName.setText(weather.place.getCity() + "," + weather.place.getCountry());
                    MainActivity.temp.setText("" + tempFormat + "°C");
                    MainActivity.humidity.setText("Humidity: " + weather.currentCondition.getHumidity() + " %");
                    MainActivity.pressure.setText("Pressure: " + weather.currentCondition.getPressure() + " hPa");
                    MainActivity.wind.setText("Wind: " + weather.wind.getSpeed() + " mps");
                    MainActivity.sunrise.setText("Sunrise: " +  weather.place.getSunrise());
                    MainActivity.sunset.setText("Sunset: " + weather.place.getSunset());
                    MainActivity.updated.setText("Last Updated: " + updateDate);
                    MainActivity.description.setText("Condition: " + weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescription() + ")");
                    MainActivity.textView0.setText(convertStringToDate.convertDay(weather.weeklyDates[0]));
                    MainActivity.textView1.setText(convertStringToDate.convertDay(weather.weeklyDates[1]));
                    MainActivity.textView2.setText(convertStringToDate.convertDay(weather.weeklyDates[2]));
                    MainActivity.textView3.setText(convertStringToDate.convertDay(weather.weeklyDates[3]));
                    MainActivity.textView4.setText(convertStringToDate.convertDay(weather.weeklyDates[4]));
                    MainActivity.textView5.setText(convertStringToDate.convertDay(weather.weeklyDates[5]));
                    MainActivity.textView6.setText(convertStringToDate.convertDay(weather.weeklyDates[6]));

                    new DownloadImageAsyncTask().execute(weather.currentCondition.getIcon(),weather.weeklyIcons[1],weather.weeklyIcons[2],weather.weeklyIcons[3],weather.weeklyIcons[4],
                            weather.weeklyIcons[5], weather.weeklyIcons[6]);

                }
                else {

                }
            }
        });
    }
}
