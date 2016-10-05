package android.test.com.theweatherapp;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import data.CityPreference;

/**
 * Created by ahmet on 9/30/2016.
 */

public class FirstFragment extends Fragment{
    public static TextView cityName,temp,description,humidity,pressure,wind,sunrise,sunset,updated,daily_tmpTxt,daily_condTxt,daily_windTxt;
    public static Button weekly_weather_btn;
    public static ImageView iconView,dayone_Img,daytwo_Img,daythree_Img,dayfour_Img,dayfive_Img,daysix_Img,dayseven_Img;
    public static TextView textView0,textView1,textView2,textView3,textView4,textView5,textView6;
    public static Button change_city_btn;

    RelativeLayout first;
    RelativeLayout second;
    RelativeLayout third;
    RelativeLayout fourth;
    RelativeLayout fifth;
    RelativeLayout sixth;
    RelativeLayout seventh;
    Context context;

    private RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment_layout, container,false);
        context = view.getContext();

        cityName = (TextView) view.findViewById(R.id.cityText);iconView = (ImageView) view.findViewById(R.id.thumbnailIcon);
        temp = (TextView) view.findViewById(R.id.tempText);description = (TextView) view.findViewById(R.id.cloudText);
        humidity = (TextView) view.findViewById(R.id.humidText);pressure =(TextView) view.findViewById(R.id.pressureText);
        wind = (TextView) view.findViewById(R.id.windText);sunrise = (TextView)view.findViewById(R.id.riseText);
        sunset = (TextView)view.findViewById(R.id.setText);updated = (TextView) view.findViewById(R.id.updateText);
        dayone_Img = (ImageView) view.findViewById(R.id.dayone_Img);
        daytwo_Img = (ImageView) view.findViewById(R.id.daytwo_Img);daythree_Img = (ImageView) view.findViewById(R.id.daythree_Img);
        dayfour_Img = (ImageView) view.findViewById(R.id.dayfour_Img);dayfive_Img = (ImageView) view.findViewById(R.id.dayfive_Img);
        daysix_Img = (ImageView) view.findViewById(R.id.daysix_Img);dayseven_Img = (ImageView) view.findViewById(R.id.dayseven_Img);
        weekly_weather_btn = (Button) view.findViewById(R.id.weekly_weather_btn);relativeLayout = (RelativeLayout) view.findViewById(R.id.YOUR_ID);
        textView0 = (TextView)view.findViewById(R.id.textView0);textView1 = (TextView)view.findViewById(R.id.textView1);
        textView2 = (TextView)view.findViewById(R.id.textView2);textView3 = (TextView)view.findViewById(R.id.textView3);
        textView4 = (TextView)view.findViewById(R.id.textView4);textView5 = (TextView)view.findViewById(R.id.textView5);
        textView6 = (TextView)view.findViewById(R.id.textView6);
        change_city_btn = (Button) view.findViewById(R.id.change_cityId);
        daily_tmpTxt = (TextView) view.findViewById(R.id.daily_temp_Txt); daily_condTxt = (TextView) view.findViewById(R.id.daily_cond_Txt);
        daily_windTxt = (TextView) view.findViewById(R.id.daily_wind_Txt);

        first=(RelativeLayout)view.findViewById(R.id.first);
        second = (RelativeLayout)view.findViewById(R.id.second);
        third = (RelativeLayout)view.findViewById(R.id.third);
        fourth = (RelativeLayout)view.findViewById(R.id.fourth);
        fifth = (RelativeLayout)view.findViewById(R.id.fifth);
        sixth = (RelativeLayout)view.findViewById(R.id.sixth);
        seventh = (RelativeLayout)view.findViewById(R.id.seventh);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyWeatherInfo(v);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyWeatherInfo(v);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyWeatherInfo(v);
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyWeatherInfo(v);
            }
        });
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyWeatherInfo(v);
            }
        });
        sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyWeatherInfo(v);
            }
        });
        seventh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyWeatherInfo(v);
            }
        });


        weekly_weather_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),Weekly_weather.class));
            }
        });
        change_city_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog(context);
            }
        });

        CityPreference cityPreference = new CityPreference(getActivity());

        renderWeatherData(cityPreference.getCity(),context);



        return view;
    }

    public void renderWeatherData (String city, Context context){
        new WeatherTask(context,city).ParseJsontoMainActivity();
    }

    private void showInputDialog(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Change City");

        final EditText cityInput= new EditText(context);
        cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
        cityInput.setHint("Ankara,TR");
        builder.setView(cityInput);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CityPreference cityPreference = new CityPreference((getActivity()));
                cityPreference.setCity(cityInput.getText().toString());

                String newCity = cityPreference.getCity();
                try {
                    renderWeatherData(newCity,context);
                    DailyWeatherInfo(first);
                }
                catch (Exception e){
                    Toast.makeText(context, "Data is Unavailable.(Main)", Toast.LENGTH_SHORT).show();
                    Log.v("exception on main: ",e.toString());
                }
            }
        });
        builder.show();
    }

    public void weekly_Weather(View v){
        Intent intent = new Intent(getContext(),Weekly_weather.class);
        startActivity(intent);
    }

    public void DailyWeatherInfo(View v){
        int i = 0;
        first.setBackgroundColor(getResources().getColor(R.color.normal_color));
        second.setBackgroundColor(getResources().getColor(R.color.normal_color));
        third.setBackgroundColor(getResources().getColor(R.color.normal_color));
        fourth.setBackgroundColor(getResources().getColor(R.color.normal_color));
        fifth.setBackgroundColor(getResources().getColor(R.color.normal_color));
        sixth.setBackgroundColor(getResources().getColor(R.color.normal_color));
        seventh.setBackgroundColor(getResources().getColor(R.color.normal_color));
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
