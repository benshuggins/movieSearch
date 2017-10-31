package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;

import static android.R.id.edit;
import static com.example.android.quakereport.R.id.title1;

public class DetailActivity extends AppCompatActivity {

    String titlePass;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final String myPrefs = "myPrefs"; // key for entire
        mContext = this;

            Intent intentToStartDetailActivity = getIntent();

            Bundle extras = intentToStartDetailActivity.getExtras();
            if (extras != null) {

        TextView title = (TextView) findViewById(title1);
        TextView score = (TextView) findViewById(R.id.score);
        TextView description = (TextView) findViewById(R.id.description);
        TextView date = (TextView) findViewById(R.id.date);
        ImageView detailImage = (ImageView)findViewById(R.id.detailImage);
        String text = intentToStartDetailActivity.getStringExtra(getString(R.string.keyTitle));
        String text1 = intentToStartDetailActivity.getStringExtra(getString(R.string.key1Vote));
        String text2 = intentToStartDetailActivity.getStringExtra(getString(R.string.key2Description));
        String text3 = intentToStartDetailActivity.getStringExtra(getString(R.string.key3Date));
        String text4 = intentToStartDetailActivity.getStringExtra(getString(R.string.key4Poster));

        title.setText(text);
        score.setText(text1);
        description.setText(text2);
        date.setText(text3);
        Picasso.with(this).load(text4).into(detailImage);

                SharedPreferences.Editor editor = getSharedPreferences(myPrefs, MODE_PRIVATE).edit();
                editor.putString("title", text);
                editor.putBoolean("title1", true);


                editor.apply();

    }

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
