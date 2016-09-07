package android.test.com.theweatherapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ahmet on 8/22/2016.
 */
public class DownloadImageAsyncTask extends AsyncTask<String,Void,Bitmap[]> {

    //setting the weather image after the doInBackground execution finished
    @Override
    protected void onPostExecute(Bitmap[] bitmap) {
        MainActivity.iconView.setImageBitmap(bitmap[0]);
        MainActivity.dayone_Img.setImageBitmap(bitmap[0]);
        MainActivity.daytwo_Img.setImageBitmap(bitmap[1]);
        MainActivity.daythree_Img.setImageBitmap(bitmap[2]);
        MainActivity.dayfour_Img.setImageBitmap(bitmap[3]);
        MainActivity.dayfive_Img.setImageBitmap(bitmap[4]);
        MainActivity.daysix_Img.setImageBitmap(bitmap[5]);
        MainActivity.dayseven_Img.setImageBitmap(bitmap[6]);
        //super.onPostExecute(bitmap);
    }

    //Building http connection to retrieve the image file on doInBackground function
    @Override
    protected Bitmap[] doInBackground(String... strings) {
        try {
            Log.v("whattheheck: ",strings[0]+"-"+strings[1]);
            return downloadImage(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap[] downloadImage(String[] code) throws IOException{
        final Bitmap[] bitmaps = new Bitmap[7];
        for(int i = 0; i<7; i++) {
            URL url = new URL("http:" + code[i]);
            final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                Log.v("bitmapsproblem: ",code[i].toString());
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                bitmaps[i] = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmaps;
    }
}
