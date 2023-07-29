package com.polyit.lab3.bai1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.polyit.lab3.R;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
  Context context;
  ArrayList<Contact> list;

  public ContactAdapter(Context context, ArrayList<Contact> list) {
    this.context = context;
    this.list = list;
  }

  @Override
  public int getCount() {
    return list.size();
  }

  @Override
  public Object getItem(int position) {
    return list.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    ViewHolder viewHolder;
    if(convertView == null){
      viewHolder = new ViewHolder();
      convertView = inflater.inflate(R.layout.bai1_list_item,null);
      viewHolder.tvName = (TextView)convertView.findViewById(R.id.tvName);
      viewHolder.tvEmail = (TextView)convertView.findViewById(R.id.tvEmail);
      viewHolder.tvMobile = (TextView)convertView.findViewById(R.id.tvMobile);
      convertView.setTag(viewHolder);
    }else{
      viewHolder = (ViewHolder)convertView.getTag();
    }
    Contact contact = list.get(position);
    viewHolder.tvName.setText("Name: "+contact.getName());
    viewHolder.tvEmail.setText("Email: "+contact.getEmail());
    viewHolder.tvMobile.setText("Mobile: "+contact.getMobile());

    return convertView;
  }
  public static class ViewHolder{
    TextView tvName, tvEmail, tvMobile;
  }
}
