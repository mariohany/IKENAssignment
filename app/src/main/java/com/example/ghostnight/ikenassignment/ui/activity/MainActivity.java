package com.example.ghostnight.ikenassignment.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.ghostnight.ikenassignment.Model.Movie;
import com.example.ghostnight.ikenassignment.R;
import com.example.ghostnight.ikenassignment.ui.fragment.MainFragment;
import com.example.ghostnight.ikenassignment.ui.fragment.SplashFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.fragment_layout);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, MainFragment.newInstance()).commit();
            }
        },3000);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, SplashFragment.newInstance()).commit();
    }

    @Override
    public void onListItemSelected(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("id", movie.getId());
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("overview", movie.getOverview());
        intent.putExtra("image", movie.getImage());
        intent.putExtra("rating", movie.getRating());
        intent.putExtra("releaseDate", movie.getRelease_date());
        intent.putExtra("adult", movie.isAdult());
        intent.putExtra("popularity", movie.getPopularity());
        intent.putExtra("language", movie.getLanguage());
        intent.putExtra("vote", movie.getVote());

        startActivity(intent);
    }
}
