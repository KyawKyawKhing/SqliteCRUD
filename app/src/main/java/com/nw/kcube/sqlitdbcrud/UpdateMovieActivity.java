package com.nw.kcube.sqlitdbcrud;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.nw.kcube.sqlitdbcrud.db.Database_Handler;
import com.nw.kcube.sqlitdbcrud.other.LineEditText;
import com.nw.kcube.sqlitdbcrud.other.app;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class UpdateMovieActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    String id, name, type, cinema, date, status, review;
    EditText movie_name, movie_type, movie_date, movie_cinema_name;
    MaterialSpinner materialSpinner;
    LineEditText movie_review;

    Button update_movie_btn;
    ImageButton cal_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie);
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

        movie_name = (EditText) findViewById(R.id.input_movie_name);
        movie_type = (EditText) findViewById(R.id.input_movie_type);
        movie_date = (EditText) findViewById(R.id.input_movie_date);
        movie_cinema_name = (EditText) findViewById(R.id.input_movie_cinema);

        materialSpinner = (MaterialSpinner) findViewById(R.id.spinner);
        materialSpinner.setItems("Already Watched", "Not");
        materialSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                status = item;
            }
        });

        movie_review = (LineEditText) findViewById(R.id.input_movie_review);

        movie_name.setText(name);
        movie_type.setText(type);
        movie_date.setText(date);
        movie_cinema_name.setText(cinema);
        movie_review.setText(review);

        cal_btn = (ImageButton) findViewById(R.id.cal_btn);
        cal_btn.setOnClickListener(this);

        update_movie_btn = (Button) findViewById(R.id.update_movie_btn);
        update_movie_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cal_btn:

                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        UpdateMovieActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(Color.parseColor("#388e3c"));
                dpd.show(getFragmentManager(), "Datepickerdialog");
                break;
            case R.id.update_movie_btn:
                name = movie_name.getText().toString();
                type = movie_type.getText().toString();
                date = movie_date.getText().toString();
                cinema = movie_cinema_name.getText().toString();
                review = movie_review.getText().toString();

                updateMovie(Integer.parseInt(id), name, type, cinema, date, status, review);
                movie_name.setText("");
                movie_type.setText("");
                movie_date.setText("");
                movie_cinema_name.setText("");
                movie_review.setText("");
//        finish();
//        Intent intent = new Intent(UpdateMovieActivity.this, MainActivity.class);
//        startActivity(intent);
                break;
        }
    }

    private void updateMovie(int id, String name, String type, String cinema, String date, String status, String review) {
        new Database_Handler(this).updateMovie(id, name, type, cinema, date, status, review);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String result = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth + "";
        movie_date.setText(result);
    }
}
