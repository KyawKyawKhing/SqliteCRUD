package com.nw.kcube.sqlitdbcrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nw.kcube.sqlitdbcrud.other.app;

public class DetailMovieActivity extends AppCompatActivity implements View.OnClickListener {
    String id, name, type, cinema, date, status, review;
    TextView show_name, show_type, show_cinema, show_date, show_status, show_review;
    Button update_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        id = getIntent().getStringExtra("id");
//        name = getIntent().getStringExtra("name");
//        type = getIntent().getStringExtra("type");
//        cinema = getIntent().getStringExtra("cinema");
//        date = getIntent().getStringExtra("date");
//        status = getIntent().getStringExtra("status");
//        review = getIntent().getStringExtra("review");

        id = app.moviedata.getId();
        name = app.moviedata.getName();
        type = app.moviedata.getType();
        cinema = app.moviedata.getCinema();
        date = app.moviedata.getDate();
        status = app.moviedata.getStatus();
        review = app.moviedata.getReview();

        show_name = (TextView) findViewById(R.id.show_movie_name);
        show_type = (TextView) findViewById(R.id.show_movie_type);
        show_cinema = (TextView) findViewById(R.id.show_movie_cinema);
        show_date = (TextView) findViewById(R.id.show_movie_date);
        show_status = (TextView) findViewById(R.id.show_movie_status);
        show_review = (TextView) findViewById(R.id.show_movie_review);
        update_btn = (Button) findViewById(R.id.update_movie_btn);
        update_btn.setOnClickListener(this);

        show_name.setText(name);
        show_type.setText(type);
        show_cinema.setText(cinema);
        show_date.setText(date);
        show_status.setText(status);
        show_review.setText(review);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DetailMovieActivity.this, UpdateMovieActivity.class);
//        intent.putExtra("id", id.toString());
//        intent.putExtra("name", name);
//        intent.putExtra("type", type);
//        intent.putExtra("cinema", cinema);
//        intent.putExtra("date", date);
//        intent.putExtra("status", status);
//        intent.putExtra("review", review);
        startActivity(intent);
    }
}
