package com.example.SmaiApp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.SmaiApp.News;
import com.example.SmaiApp.R;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    public NewsAdapter(Context context, int layout, List<News> newsList) {
        myContext = context;
        myLayout = layout;
        arrayNews = newsList;
    }

    Context myContext;
    int myLayout;
    List<News> arrayNews;

    @Override
    public int getCount() {
        return arrayNews.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(myLayout, null);

        TextView txtName = convertView.findViewById(R.id.tv_tittle);
        txtName.setText(arrayNews.get(position).Tittle);

        TextView txtCatalog = convertView.findViewById(R.id.tv_catalog);
        txtCatalog.setText(arrayNews.get(position).Catalog);

        TextView txtNote = convertView.findViewById(R.id.tv_note);
        txtNote.setText(arrayNews.get(position).Note);

        TextView txtAddress = convertView.findViewById(R.id.tv_address);
        txtAddress.setText(arrayNews.get(position).Address);

        TextView txtTypesNews = convertView.findViewById(R.id.tv_typenews);
        txtTypesNews.setText(arrayNews.get(position).TypeNews);

        TextView txtDatePost = convertView.findViewById(R.id.tv_datepost);
        txtDatePost.setText(arrayNews.get(position).DatePost);

        ImageView imgHinh = convertView.findViewById(R.id.img_hinh);
        imgHinh.setImageResource((arrayNews.get(position).UrlImage));
        return convertView;
    }
}
