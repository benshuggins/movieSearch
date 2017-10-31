package com.example.android.quakereport;


import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {


    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    final static String API_KEY = "?api_key=30326cfda5a686ec399218dd1dfa8997";

    final static String GITHUB_BASE_URL = "http://api.themoviedb.org/3/movie/";

    // private static final String MOST_POPULAR_URL = "https://api.themoviedb.org/3/movie/popular?api_key=30326cfda5a686ec399218dd1dfa8997";

    // private static final String HIGHEST_RATED_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=30326cfda5a686ec399218dd1dfa8997";


    final static String POPULAR = "popular";

    final static String RATED = "top_rated";


    private QueryUtils() {
    }

    public static List<Movie> fetchMovieData(String requestUrl) {

        URL url = createUrl(requestUrl);


        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }


        List<Movie> movies = extractFeatureFromJson(jsonResponse);


        return movies;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    public static URL buildUrl() {


        Uri builtUri = Uri.parse(GITHUB_BASE_URL).buildUpon()       // this is the uri builder it allows us to use uri without dealing with all of the components teh builder handles
                .appendEncodedPath(POPULAR)
                .appendEncodedPath(API_KEY)//"q", // so this is modifying the url and adding in another query after q=
                //.appendQueryParameter(PARAM_SORT, API_KEY)
                .build();


        URL url = null;  // ok this converts uri to a url
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) { //this catches the exception
            e.printStackTrace();

        }
        return url;
    }

    public static URL buildUrl1() {

        Uri builtUri = Uri.parse(GITHUB_BASE_URL).buildUpon()       // this is the uri builder it allows us to use uri without dealing with all of the components teh builder handles
                .appendEncodedPath(RATED)
                .appendEncodedPath(API_KEY)
                .build(); //apendEncodedPath doesn't throw out weird characters, why with .appendQueryPath

        URL url = null;  // ok this converts uri to a url
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) { //this catches the exception
            e.printStackTrace();

        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Movie> extractFeatureFromJson(String movieJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(movieJSON)) {
            return null;
        }

        List<Movie> movies = new ArrayList<>();


        try {


            JSONObject movieJson = new JSONObject(movieJSON);
            JSONArray movieArray = movieJson.getJSONArray("results");


            for (int i = 0; i < movieArray.length(); i++) {
                JSONObject movie = movieArray.getJSONObject(i);

                String voteAverage = movie.getString("vote_average");

                String title = movie.getString("title");

//                 "w92", "w154", "w185", "w342", "w500", "w780", or "original". For most phones we recommend using “w185”.
                //And finally the poster path returned by the query, in this case

                String posterPath = (("http://image.tmdb.org/t/p/w185" + movie.getString("poster_path")));

                String overView = movie.getString("overview");

                String releaseDate = movie.getString("release_date");


                Movie movie1 = new Movie(voteAverage, title, posterPath, overView, releaseDate);

                // Add the new {@link Movie} to the list of movies.
                movies.add(movie1);
            }
        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of movies
        return movies;
    }

}