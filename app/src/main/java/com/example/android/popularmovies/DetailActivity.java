package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.Models.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView mImageViewPoster;
    private TextView mTextViewOverview;
    private TextView mTextViewReleaseDate;
    private TextView mTextViewRating;
    private TextView mTextViewTitle;

    private static final String INTENT_KEY = "movie_detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mImageViewPoster = findViewById(R.id.iv_poster);
        mTextViewReleaseDate = findViewById(R.id.tv_release_date);
        mTextViewRating = findViewById(R.id.tv_user_rating);
        mTextViewOverview = findViewById(R.id.tv_overview);
        mTextViewTitle = findViewById(R.id.tv_title);

        Intent intentCalled = getIntent();
        if (intentCalled != null) {
            if (intentCalled.hasExtra(INTENT_KEY)) {
                Movie movie = (Movie) intentCalled.getSerializableExtra(INTENT_KEY);

                String imageURL = "http://image.tmdb.org/t/p/w185"+movie.getPosterPath();
                Picasso.with(this).load(imageURL).into(mImageViewPoster);

                mTextViewTitle.setText(movie.getTitle());
                mTextViewReleaseDate.setText(movie.getReleaseDate());
                mTextViewRating.setText(movie.getVoteCount().toString());
                mTextViewOverview.setText(movie.getOverview());

            }
        }

    }
}
