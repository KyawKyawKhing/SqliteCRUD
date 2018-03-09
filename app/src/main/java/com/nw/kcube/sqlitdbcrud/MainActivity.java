package com.nw.kcube.sqlitdbcrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nw.kcube.sqlitdbcrud.adapter.MovieViewAdapter;
import com.nw.kcube.sqlitdbcrud.db.Database_Handler;
import com.nw.kcube.sqlitdbcrud.model.movie;
import com.nw.kcube.sqlitdbcrud.other.RecyclerTouchListener;
import com.nw.kcube.sqlitdbcrud.other.app;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView movie_listview;
    MovieViewAdapter movieViewAdapter;
    ArrayList<movie> movieArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        movie_listview = (RecyclerView) findViewById(R.id.movie_recyclerVew);
        movieArrayList = new ArrayList<>();
        movieArrayList = new Database_Handler(this).getMovieList("select * from movies");
        movieViewAdapter = new MovieViewAdapter(MainActivity.this, movieArrayList);
        movie_listview.setAdapter(movieViewAdapter);
        movie_listview.setLayoutManager(new LinearLayoutManager(this));
        movie_listview.addOnItemTouchListener(
                new RecyclerTouchListener(getApplicationContext(), movie_listview, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        app.moviedata = movieArrayList.get(position);
                        Intent detailintent = new Intent(MainActivity.this, DetailMovieActivity.class);
//                        detailintent.putExtra("id", movieArrayList.get(position).getId());
//                        detailintent.putExtra("name", movieArrayList.get(position).getName());
//                        detailintent.putExtra("type", movieArrayList.get(position).getType());
//                        detailintent.putExtra("cinema", movieArrayList.get(position).getCinema());
//                        detailintent.putExtra("date", movieArrayList.get(position).getDate());
//                        detailintent.putExtra("status", movieArrayList.get(position).getStatus());
//                        detailintent.putExtra("review", movieArrayList.get(position).getReview());
                        startActivity(detailintent);
                    }

                    @Override
                    public void onLongClick(View v, int position) {

                    }
                })
        );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMovieActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        movieArrayList = new Database_Handler(this).getMovieList("select * from movies");
        movieViewAdapter.SwapList(movieArrayList);
    }
}
