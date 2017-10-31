
package com.example.android.quakereport;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MovieActivity extends AppCompatActivity {
    public static final String LOG_TAG = MovieActivity.class.getName();

    private MovieAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity);


        // ArrayList<Movie> movies = QueryUtils.extractEarthquakes();
        final GridView movieListView = (GridView) findViewById(R.id.list);
        mAdapter = new MovieAdapter(this, new ArrayList<Movie>());

        movieListView.setAdapter(mAdapter);
        // Create a new {@link ArrayAdapter} of movies
        //final MovieAdapter adapter = new MovieAdapter(this, movies);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        //movieListView.setAdapter(adapter);
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Movie MovieAsyncTask = mAdapter.getItem(position);

                //Movie MovieAsyncTask = getItem(position);

                String title = MovieAsyncTask.getPlace();

                String vote = MovieAsyncTask.getVoteAverage();

                String description = MovieAsyncTask.getoverView();

                String date = MovieAsyncTask.getReleaseDate();

                String poster = MovieAsyncTask.getPoster();


                if (position < movieListView.getAdapter().getCount()) {

                    Intent appInfo = new Intent(MovieActivity.this, DetailActivity.class);
                    // Need to send data title, etc to new activity

                    appInfo.putExtra(getString(R.string.keyTitle), title);
                    appInfo.putExtra(getString(R.string.key2Description), description);
                    appInfo.putExtra(getString(R.string.key1Vote), vote);
                    appInfo.putExtra(getString(R.string.key3Date), date);
                    appInfo.putExtra(getString(R.string.key4Poster), poster);

                    startActivity(appInfo);

                }
            }
        });

        loadmostpopular();

    }

    private void loadhighestrated() {  //ok so right here I need to modify the url in uribuilder


        //String location = HIGHEST_RATED_URL;
        URL githubSearchUrl = QueryUtils.buildUrl1();

        String git1 = githubSearchUrl.toString();


        new MovieAsyncTask().execute(git1);
    }

    private void loadmostpopular() {  //ok so right here I need to modify the url in uribuilder


        URL githubSearchUrl = QueryUtils.buildUrl();

        String git = githubSearchUrl.toString();


        new MovieAsyncTask().execute(git);
    }







    private class MovieAsyncTask extends AsyncTask<String, Void, List<Movie>> {


        @Override
        protected List<Movie> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Movie> result = QueryUtils.fetchMovieData(urls[0]);
            return result;
        }


        @Override
        protected void onPostExecute(List<Movie> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Movie}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.most_popular) {
            loadmostpopular();
            return true;
        }
        if (id == R.id.highest_rated) {
            loadhighestrated();
            return true;
        }
        if (id == R.id.action_settings){
            Intent startSettingsActivity = new Intent(this, FavoritesActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}





