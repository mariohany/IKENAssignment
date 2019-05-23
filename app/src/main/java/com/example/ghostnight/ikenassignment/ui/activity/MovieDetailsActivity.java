package com.example.ghostnight.ikenassignment.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ghostnight.ikenassignment.R;


public class MovieDetailsActivity extends AppCompatActivity {


    private ImageView mImage;
    private TextView mTitle;
    private TextView mRating;
    private TextView mReleaseDate;
    private TextView mOverview;
    private TextView mAdult;
    private TextView mPopularity;
    private TextView mLanguage;
    private TextView mVote;

    private int id;
    private String title;
    private String overview;
    private String releaseDate;
    private float rating;
    private String image;
    private float popularity;
    private boolean adult;
    private String language;
    private int vote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        overview = getIntent().getStringExtra("overview");
        releaseDate = getIntent().getStringExtra("releaseDate");
        rating = getIntent().getFloatExtra("rating", 0);
        image = getIntent().getStringExtra("image");
        popularity = getIntent().getFloatExtra("popularity", 0);
        adult = getIntent().getBooleanExtra("adult", false);
        language = getIntent().getStringExtra("language");
        vote = getIntent().getIntExtra("vote",0);

        mImage = findViewById(R.id.image);
        mTitle = findViewById(R.id.title);
        mRating = findViewById(R.id.rating);
        mReleaseDate = findViewById(R.id.release_date);
        mOverview = findViewById(R.id.overview);
        mPopularity = findViewById(R.id.popularity);
        mAdult = findViewById(R.id.adult);
        mLanguage = findViewById(R.id.language);
        mVote = findViewById(R.id.vote);

        if(image != null) {
            Glide.with(this)
                    .load(image)
                    .into(mImage);
        }else{
            mImage.setImageResource(R.drawable.poster_holder);
        }
        mTitle.setText(title);
        mRating.setText(String.valueOf(rating));
        mReleaseDate.setText(releaseDate);
        mOverview.setText(overview);
        mLanguage.setText(language);
        mPopularity.setText(String.valueOf(popularity));
        mVote.setText(String.valueOf(vote));
        if(adult)
            mAdult.setText("True");
        else
            mAdult.setText("False");

    }
}
