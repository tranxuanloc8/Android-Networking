package com.polyit.lab3.Bai3;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polyit.lab3.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
  private ArrayList<AndroidVersion> android;
  public DataAdapter(ArrayList<AndroidVersion> android) {
    this.android = android;
  }
  @Override
  public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int
          viewType) {
    View view =
            LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent,
                    false);
    return new ViewHolder(view);
  }
  @Override
  public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {
  }
  @Override
  public int getItemCount() {
    return android.size();
  }
  public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView tv_name,tv_version,tv_api_level;
    public ViewHolder(View view) {
      super(view);
      tv_name = (TextView)view.findViewById(R.id.tv_name);
      tv_version = (TextView)view.findViewById(R.id.tv_version);
      tv_api_level = (TextView)view.findViewById(R.id.tv_api_level);
    }
  }
}
