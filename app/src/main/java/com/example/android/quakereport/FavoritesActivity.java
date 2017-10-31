package com.example.android.quakereport;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.quakereport.R.attr.textAllCaps;
import static com.example.android.quakereport.R.attr.title;
import static com.example.android.quakereport.R.string.pref_show_favorites_key;
import static java.lang.reflect.Modifier.FINAL;


public class FavoritesActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    //How do I send just teh data I want to a listView????

    //I need onPreferenceCHangeListener now that I can see what is going on, update the ui from the listener

    //you should possibly use getSharedPreferences Instead of getDefaukltSharedPreferences

    //Then look at SQL project that saves these item
    //Look at adding trailers (just Intent to launch)
    //

        public static String DEFAULT = "no value";
        private Context mContext;
    final String myPrefs = "myPrefs"; // key for entire
    TextView seeThis = (TextView)findViewById(R.id.favorites_list);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);



      // TextView seeThis = (TextView)findViewById(R.id.favorites_list);

      // SharedPreferences sharedPreferences = getSharedPreferences(myPrefs, Context.MODE_PRIVATE);
       //git String title = sharedPreferences.getString("title", "");

        //How do I display if item is checked??? ANd multiple items
        //


   /**     final SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("title1", true)){ //key, default Value
            editor.apply();
            seeThis.setText(title);

        }else if(sharedPreferences.getBoolean("title1", false)){
            seeThis.setText("not this");
        }**/
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences settings = getSharedPreferences(myPrefs, Context.MODE_PRIVATE);
        String title = settings.getString("title", "");
        if (key.equals(getString(R.string.pref_show_favorites_key))){
            if(sharedPreferences.getBoolean(key, getResources().getBoolean(R.bool.pref_show_fav_value))==true);
            //we want to set the title now in a listview
            seeThis.setText(title);
        }

    }
}