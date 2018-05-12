package com.example.android.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.popularmovies.Adapters.MovieAdapter;
import com.example.android.popularmovies.Models.Movie;
import com.example.android.popularmovies.Utils.JsonUtils;
import com.example.android.popularmovies.Utils.NetworkUtility;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final int MOVIES_LOADER = 22;

    private GridView mGridView;
    private MovieAdapter mMovieAdapter;
    private Movie[] moviesList;

    private static final String INTENT_KEY = "movie_detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = findViewById(R.id.gridView);
        loadMovies();

        new FetchMoviesTask().execute("hh");

    }


    private void loadMovies() {
        if (moviesList == null) {
            moviesList = new Movie[0];
        }

        mMovieAdapter = new MovieAdapter(MainActivity.this,moviesList);
        mGridView.setAdapter(mMovieAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = moviesList[position];
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(INTENT_KEY,movie);
                startActivity(intent);
            }

        });
    }


    public class FetchMoviesTask extends AsyncTask<String,Void,Movie[]> {

        @Override
        protected Movie[] doInBackground(String... params) {
            URL moviesURL = NetworkUtility.buildUrl();
            try {
                String movieSearchResults = NetworkUtility.getResponseFromHttpUrl(moviesURL);
                Log.v("Results:",movieSearchResults);


                return JsonUtils.getMoviesFromJSONString(movieSearchResults);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            moviesList = movies;
            loadMovies();
        }
    }



}
