package com.nw.kcube.sqlitdbcrud.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.nw.kcube.sqlitdbcrud.R;
import com.nw.kcube.sqlitdbcrud.model.movie;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MovieViewAdapter extends RecyclerView.Adapter<MovieViewAdapter.MyViewHolder> {
    private Context context;
    private List<movie> list = Collections.emptyList();
    private LayoutInflater inflater;
    private String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    public void SwapList(List<movie> userlist) {
        this.list = userlist;
        notifyDataSetChanged();
    }

    public MovieViewAdapter(Context context, List<movie> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.movie_name.setText(list.get(position).getName());
        holder.movie_type.setText(list.get(position).getType());
        holder.movie_cinema.setText(list.get(position).getCinema());
        holder.movie_date.setText(list.get(position).getDate());
        String name = list.get(position).getName();
        String firstCharName = name.substring(0, 1);
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRound(firstCharName, getColor());
        holder.imgView.setImageDrawable(drawable);

    }

    public int getColor() {
        String color;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColors.length);
        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);
        return colorAsInt;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView movie_name;
        TextView movie_type;
        TextView movie_cinema;
        TextView movie_date;

        MyViewHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.ivTextDrawable);
            movie_name = (TextView) itemView.findViewById(R.id.list_movie_name);
            movie_type = (TextView) itemView.findViewById(R.id.list_movie_type);
            movie_cinema = (TextView) itemView.findViewById(R.id.list_moview_cinema);
            movie_date = (TextView) itemView.findViewById(R.id.list_moview_date);
        }
    }
}
