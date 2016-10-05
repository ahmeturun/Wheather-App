package android.test.com.theweatherapp;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        FirstFragment firstFragment = new FirstFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, firstFragment, "FirstFragment");
        transaction.commit();



        //blurrying the background
        //Bitmap myBackgroundFile = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.beach);
        //Bitmap blurredBitmap = BlurBuilder.blur(MainActivity.this,myBackgroundFile);
        //relativeLayout.setBackgroundDrawable( new BitmapDrawable( getResources(), blurredBitmap ) );

    }
}
