package com.example.retrofittest3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BoxOfficeAdapter extends RecyclerView.Adapter<BoxOfficeAdapter.BoxOfficeViewHolder> {

    List<WeeklyBoxOfficeList> weeklyBoxOfficeLists = new ArrayList<>();
    Context context;

    public BoxOfficeAdapter(List<WeeklyBoxOfficeList> weeklyBoxOfficeLists, Context context) {
        this.weeklyBoxOfficeLists = weeklyBoxOfficeLists;
        this.context = context;
    }

    @NonNull
    @Override
    public BoxOfficeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.boxoffice_item, parent, false);
        BoxOfficeViewHolder boxOfficeViewHolder = new BoxOfficeViewHolder(rootView);
        return boxOfficeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoxOfficeViewHolder holder, int position) {
        WeeklyBoxOfficeList weeklyBoxOfficeList = weeklyBoxOfficeLists.get(position);
        holder.rank.setText(weeklyBoxOfficeList.getRank()+"");
        holder.movie_name.setText(weeklyBoxOfficeList.getMovieNm());
        holder.count1.setText(weeklyBoxOfficeList.getAudiCnt()); //일일 관객수
        holder.count2.setText(weeklyBoxOfficeList.getAudiAcc()); //누적관객수
    }

    @Override
    public int getItemCount() {
        return weeklyBoxOfficeLists.size();
    }

    public class BoxOfficeViewHolder extends RecyclerView.ViewHolder {

        TextView rank;
        TextView movie_name;
        TextView count1;
        TextView count2;

        public BoxOfficeViewHolder(@NonNull View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.rank);
            movie_name = itemView.findViewById(R.id.movie_name);
            count1 = itemView.findViewById(R.id.count1);
            count2 = itemView.findViewById(R.id.count2);
        }
    }
}
