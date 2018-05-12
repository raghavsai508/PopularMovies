package com.example.android.popularmovies.Utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtility {

    private static final String TAG = NetworkUtility.class.getSimpleName();

    private static final String MOVIE_BASE_URL = "api.themoviedb.org";
    private static final String MOVIE_POPULAR_PATH = "3/movie/popular";
    private static final String MOVIE_TOP_RATED_PATH = "3/movie/top_rated";

    private static final String MOVIE_API_KEY = "";
    private static final String MOVIE_QUERY_API_PARAM = "api_key";


    public static URL buildUrl() {
//        Uri uri = Uri.parse(MOVIE_BASE_URL).buildUpon()
//                .appendPath(MOVIE_POPULAR_PATH)
//                .appendQueryParameter(MOVIE_QUERY_API_PARAM,MOVIE_API_KEY)
//                .build();

        Uri.Builder builder = new Uri.Builder();
        Uri uri =  builder.scheme("https")
                .authority(MOVIE_BASE_URL)
                .appendPath("3")
                .appendPath("movie")
                .appendPath("popular")
                .appendQueryParameter(MOVIE_QUERY_API_PARAM,MOVIE_API_KEY)
                .build();


        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Log.d(TAG, "Built URI " + url);

        return url;

    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        Log.d(TAG, "Built URI " + url);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }



}
