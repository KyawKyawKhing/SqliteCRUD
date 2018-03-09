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
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class AddMovieActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    EditText movie_name, movie_type, movie_date, movie_cinema_name;
    ImageButton cal_btn;
    MaterialSpinner materialSpinner;
    LineEditText movie_review;

    Button add_movie_btn;
    String name, type, date, cinema, status, review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        movie_name = (EditText) findViewById(R.id.input_movie_name);
        movie_type = (EditText) findViewById(R.id.input_movie_type);
        movie_date = (EditText) findViewById(R.id.input_movie_date);
        movie_cinema_name = (EditText) findViewById(R.id.input_movie_cinema);

        movie_review = (LineEditText) findViewById(R.id.input_movie_review);

        status = "Already Watched";


        materialSpinner = (MaterialSpinner) findViewById(R.id.spinner);
        materialSpinner.setItems("Already Watched", "Not");
        materialSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                status = item;
            }
        });
        cal_btn = (ImageButton) findViewById(R.id.cal_btn);
        cal_btn.setOnClickListener(this);

        add_movie_btn = (Button) findViewById(R.id.add_moview_btn);
        add_movie_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cal_btn:
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AddMovieActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(Color.parseColor("#388e3c"));
                dpd.show(getFragmentManager(), "Datepickerdialog");
                break;
            case R.id.add_moview_btn:
                name = movie_name.getText().toString();
                type = movie_type.getText().toString();
                date = movie_date.getText().toString();
                cinema = movie_cinema_name.getText().toString();
                review = movie_review.getText().toString();

                addMovie(name, type, cinema, date, status, review);
                movie_name.setText("");
                movie_type.setText("");
                movie_date.setText("");
                movie_cinema_name.setText("");
                movie_review.setText("");
                break;
        }
    }

    private void addMovie(String name, String type, String cinema, String date, String status, String review) {
        new Database_Handler(this).insertMovie(name, type, cinema, date, status, review);
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String result = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth + "";
        movie_date.setText(result);
    }
}
