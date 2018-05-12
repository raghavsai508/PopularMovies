package com.example.android.popularmovies.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.popularmovies.Models.Movie;
import com.example.android.popularmovies.R;
import com.squareup.picasso.Picasso;

public class MovieAdapter extends ArrayAdapter<Movie> {

    private Context mContext;
    private Movie[] movies;

    public MovieAdapter(Context context, Movie[] moviesList) {
        super(context,0,moviesList);
        mContext = context;
        movies = moviesList;
    }


    @Override
    public int getCount() {
        return movies.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View movieView = convertView;
        if (movieView == null) {
            movieView = LayoutInflater.from(mContext).inflate(R.layout.movie_list_item, parent,false);
        }

        Movie movie = movies[position];
        ImageView movieImage = movieView.findViewById(R.id.imageView);

        String imageURL = "http://image.tmdb.org/t/p/w185"+movie.getPosterPath();

        Picasso.with(mContext).load(imageURL).into(movieImage);

        return movieView;
    }


}
