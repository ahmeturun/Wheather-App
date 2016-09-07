package android.test.com.theweatherapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ahmet on 8/18/2016.
 */

public class Weekly_weather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_weather);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container,new PlaceholderFragment());
        }
        else {
            Log.v("aoooo: ","aaaaaaa");
        }

    }

    public static class PlaceholderFragment extends Fragment{
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_simple,container,false);
            Log.v("are we there: ","jghjghj");
            final TextView textView = (TextView) rootView.findViewById(R.id.tmprtText);
            final Button setTextBtn = (Button) rootView.findViewById(R.id.fragmentBtn);
            setTextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(),"came OON!",Toast.LENGTH_LONG);
                    setTextBtn.setText("aaaaaaaaaa");
                    textView.setText("Temperature: Whatever we get");
                }
            });


            return rootView;
        }
    }
}
