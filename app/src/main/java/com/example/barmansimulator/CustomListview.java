package com.example.barmansimulator;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomListview extends ArrayAdapter<String> {

    private Integer[] imgs;
    private Activity context;
    private String[] names;

    public CustomListview(Activity context, Integer[] imgs, String[] names) {
        super(context, R.layout.listview_layout,names);
        this.context = context;
        this.imgs = imgs;
        this.names = names;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;

        if(view == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.listview_layout,null,true);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.imv.setImageResource(imgs[position]);

        return view;
    }

    class ViewHolder{
        ImageView imv;
        TextView tx;
        public ViewHolder(View view){
            imv = view.findViewById(R.id.imageView);

        }
    }
}
