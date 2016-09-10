package android.test.com.theweatherapp;

import android.os.AsyncTask;
import android.util.Log;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Weather;

/**
 * Created by ahmet on 8/22/2016.
 */
//Setting function for getting weather information from the web
public class WeatherTask extends AsyncTask<String, Void, Weather> {
    Weather weather ;

    @Override
    protected Weather doInBackground(String... strings) {
        String data = ( ( new WeatherHttpClient()).getWeatherData(strings[0]));

        weather = JSONWeatherParser.getWeather(data);
        if(weather!=null){
            weather.iconData = weather.currentCondition.getIcon();
        }
        //Log.v("Data: ", weather.currentCondition.getDescription());

        //new DoownloadImageAsyncTask().execute(weather.iconData);

        return weather;
    }

    @Override
    protected void onPostExecute(Weather weather) {

        super.onPostExecute(weather);

        DateFormat df = DateFormat.getDateTimeInstance(1,3);
        if(weather!=null) {
            String sunriseDate = weather.place.getSunrise();
            String sunsetDate = weather.place.getSunset();

            String updateDate = convertStringToDate.convertDate(weather.place.getLastUpdate()).toLocaleString();

            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String tempFormat = decimalFormat.format(weather.currentCondition.getTemperature());
            final double tempdouble = weather.currentCondition.getTemperature();

            MainActivity.cityName.setText(weather.place.getCity() + "," + weather.place.getCountry());
            MainActivity.temp.setText("" + tempFormat + "°C");
            MainActivity.humidity.setText("Humidity: " + weather.currentCondition.getHumidity() + " %");
            MainActivity.pressure.setText("Pressure: " + weather.currentCondition.getPressure() + " hPa");
            MainActivity.wind.setText("Wind: " + weather.wind.getSpeed() + " mps");
            MainActivity.sunrise.setText("Sunrise: " + sunriseDate);
            MainActivity.sunset.setText("Sunset: " + sunsetDate);
            MainActivity.updated.setText("Last Updated: " + updateDate);
            MainActivity.description.setText("Condition: " + weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescription() + ")");
            MainActivity.textView0.setText(convertStringToDate.convertDay(weather.weeklyDates[0]));
            MainActivity.textView1.setText(convertStringToDate.convertDay(weather.weeklyDates[1]));
            MainActivity.textView2.setText(convertStringToDate.convertDay(weather.weeklyDates[2]));
            MainActivity.textView3.setText(convertStringToDate.convertDay(weather.weeklyDates[3]));
            MainActivity.textView4.setText(convertStringToDate.convertDay(weather.weeklyDates[4]));
            MainActivity.textView5.setText(convertStringToDate.convertDay(weather.weeklyDates[5]));
            MainActivity.textView6.setText(convertStringToDate.convertDay(weather.weeklyDates[6]));

            new DownloadImageAsyncTask().execute(weather.iconData,weather.weeklyIcons[1],weather.weeklyIcons[2],weather.weeklyIcons[3],weather.weeklyIcons[4],
                    weather.weeklyIcons[5], weather.weeklyIcons[6]);

            Log.v("iconpathweekly: ",weather.weeklyIcons[0]);

        }
        else {
        }
    }
}
