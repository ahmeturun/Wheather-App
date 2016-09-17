package android.test.com.theweatherapp;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import blur.BlurBuilder;
import data.CityPreference;
import model.Weather;

/**
 * Created by ahmet on 8/18/2016.
 */

public class MainActivity extends FragmentActivity {

    public static TextView cityName,temp,description,humidity,pressure,wind,sunrise,sunset,updated,daily_tmpTxt,daily_condTxt,daily_windTxt;
    public static Button weekly_weather_btn;
    public static ImageView iconView,dayone_Img,daytwo_Img,daythree_Img,dayfour_Img,dayfive_Img,daysix_Img,dayseven_Img;
    public static TextView textView0,textView1,textView2,textView3,textView4,textView5,textView6;
    public static Button change_city_btn;

    Weather weather ;

    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cityName = (TextView) findViewById(R.id.cityText);iconView = (ImageView) findViewById(R.id.thumbnailIcon);
        temp = (TextView) findViewById(R.id.tempText);description = (TextView) findViewById(R.id.cloudText);
        humidity = (TextView) findViewById(R.id.humidText);pressure =(TextView) findViewById(R.id.pressureText);
        wind = (TextView) findViewById(R.id.windText);sunrise = (TextView)findViewById(R.id.riseText);
        sunset = (TextView)findViewById(R.id.setText);updated = (TextView) findViewById(R.id.updateText);
        dayone_Img = (ImageView) findViewById(R.id.dayone_Img);
        daytwo_Img = (ImageView) findViewById(R.id.daytwo_Img);daythree_Img = (ImageView) findViewById(R.id.daythree_Img);
        dayfour_Img = (ImageView) findViewById(R.id.dayfour_Img);dayfive_Img = (ImageView) findViewById(R.id.dayfive_Img);
        daysix_Img = (ImageView) findViewById(R.id.daysix_Img);dayseven_Img = (ImageView) findViewById(R.id.dayseven_Img);
        weekly_weather_btn = (Button) findViewById(R.id.weekly_weather_btn);relativeLayout = (RelativeLayout) findViewById(R.id.YOUR_ID);
        textView0 = (TextView)findViewById(R.id.textView0);textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);textView5 = (TextView)findViewById(R.id.textView5);
        textView6 = (TextView)findViewById(R.id.textView6);
        change_city_btn = (Button) findViewById(R.id.change_cityId);
        daily_tmpTxt = (TextView) findViewById(R.id.daily_temp_Txt); daily_condTxt = (TextView) findViewById(R.id.daily_cond_Txt);
        daily_windTxt = (TextView) findViewById(R.id.daily_wind_Txt);


        //blurrying the background
        Bitmap myBackgroundFile = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.beach);
        Bitmap blurredBitmap = BlurBuilder.blur(MainActivity.this,myBackgroundFile);
        relativeLayout.setBackgroundDrawable( new BitmapDrawable( getResources(), blurredBitmap ) );

        weekly_weather_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Weekly_weather.class));
            }
        });
        change_city_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });

        CityPreference cityPreference = new CityPreference(MainActivity.this);

        renderWeatherData(cityPreference.getCity());

    }

    public void renderWeatherData (String city){
        new WeatherTask(this.getApplicationContext(),city).ParseJsontoMainActivity();
    }

    private void showInputDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Change City");

        final EditText cityInput= new EditText((MainActivity.this));
        cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
        cityInput.setHint("Ankara,TR");
        builder.setView(cityInput);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CityPreference cityPreference = new CityPreference((MainActivity.this));
                cityPreference.setCity(cityInput.getText().toString());

                String newCity = cityPreference.getCity();
                renderWeatherData(newCity);
                DailyWeatherInfo(findViewById(R.id.first));
            }
        });
        builder.show();
    }

    public void weekly_Weather(View v){
        Intent intent = new Intent(getApplicationContext(),Weekly_weather.class);
        startActivity(intent);
    }

    public void DailyWeatherInfo(View v){
        int i = 0;
        findViewById(R.id.first).setBackgroundColor(getResources().getColor(R.color.normal_color));
        findViewById(R.id.second).setBackgroundColor(getResources().getColor(R.color.normal_color));
        findViewById(R.id.third).setBackgroundColor(getResources().getColor(R.color.normal_color));
        findViewById(R.id.fourth).setBackgroundColor(getResources().getColor(R.color.normal_color));
        findViewById(R.id.fifth).setBackgroundColor(getResources().getColor(R.color.normal_color));
        findViewById(R.id.sixth).setBackgroundColor(getResources().getColor(R.color.normal_color));
        findViewById(R.id.seventh).setBackgroundColor(getResources().getColor(R.color.normal_color));
        switch (v.getId()){
            case R.id.first:
                i = 0;
                daily_tmpTxt.setText("Temp: "+WeatherTask.daily_temp[i]);
                daily_condTxt.setText(WeatherTask.daily_condition[i]);
                daily_windTxt.setText("Wind: "+WeatherTask.daily_wind[i]);
                v.setBackgroundColor(getResources().getColor(R.color.daily_weather_color));
                break;
            case R.id.second:
                i = 1;
                daily_tmpTxt.setText("Temp: "+WeatherTask.daily_temp[i]);
                daily_condTxt.setText(WeatherTask.daily_condition[i]);
                daily_windTxt.setText("Wind: "+WeatherTask.daily_wind[i]);
                v.setBackground(getResources().getDrawable(R.drawable.top_corners_rounded));
                v.setBackgroundColor(getResources().getColor(R.color.daily_weather_color));
                break;
            case R.id.third:
                i = 2;
                daily_tmpTxt.setText("Temp: "+WeatherTask.daily_temp[i]);
                daily_condTxt.setText(WeatherTask.daily_condition[i]);
                daily_windTxt.setText("Wind: "+WeatherTask.daily_wind[i]);
                v.setBackgroundColor(getResources().getColor(R.color.daily_weather_color));
                break;
            case R.id.fourth:
                i = 3;
                daily_tmpTxt.setText("Temp: "+WeatherTask.daily_temp[i]);
                daily_condTxt.setText(WeatherTask.daily_condition[i]);
                daily_windTxt.setText("Wind: "+WeatherTask.daily_wind[i]);
                v.setBackgroundColor(getResources().getColor(R.color.daily_weather_color));
                break;
            case R.id.fifth:
                i = 4;
                daily_tmpTxt.setText("Temp: "+WeatherTask.daily_temp[i]);
                daily_condTxt.setText(WeatherTask.daily_condition[i]);
                daily_windTxt.setText("Wind: "+WeatherTask.daily_wind[i]);
                v.setBackgroundColor(getResources().getColor(R.color.daily_weather_color));
                break;
            case R.id.sixth:
                i = 5;
                daily_tmpTxt.setText("Temp: "+WeatherTask.daily_temp[i]);
                daily_condTxt.setText(WeatherTask.daily_condition[i]);
                daily_windTxt.setText("Wind: "+WeatherTask.daily_wind[i]);
                v.setBackgroundColor(getResources().getColor(R.color.daily_weather_color));
                break;
            case R.id.seventh:
                i = 6;
                daily_tmpTxt.setText("Temp: "+WeatherTask.daily_temp[i]);
                daily_condTxt.setText(WeatherTask.daily_condition[i]);
                daily_windTxt.setText("Wind: "+WeatherTask.daily_wind[i]);
                v.setBackgroundColor(getResources().getColor(R.color.daily_weather_color));
                break;
        }
    }

}
