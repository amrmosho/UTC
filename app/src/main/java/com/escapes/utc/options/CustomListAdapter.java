package com.escapes.utc.options;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.*;

import com.escapes.utc.R;
import com.escapes.utc.libs.uitls;

import java.util.ArrayList;

/**
 * Created by karma on 02/05/2015.
 */
public class CustomListAdapter extends BaseAdapter {

    private ArrayList listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context context, ArrayList listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_layout, null);
            holder = new ViewHolder();
          //  holder.headlineView = (TextView) convertView.findViewById(R.id.title);


            holder.reporterNameView = (TextView) convertView.findViewById(R.id.txt);
            holder.reportedDateView = (TextView) convertView.findViewById(R.id.cur);
            holder.idDateView = (TextView) convertView.findViewById(R.id.myid);

            //    holder.imageView = (ImageView) convertView.findViewById(R.id.flag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        uitls u = new uitls();

      ListItem newsItem = (ListItem) listData.get(position);
      // String a=(newsItem.getHeadline());
         holder.reporterNameView.setText(newsItem.getHeadline());
        holder.reportedDateView.setText(newsItem.getListDes());
        holder.idDateView.setText(newsItem.getId());



      // holder.reportedDateView.setText(newsItem.getDate());
      //  holder.imageView.setImageBitmap(u.getImageFromUrl(newsItem.getUrl()));
        return convertView;
    }

    static class ViewHolder {
        TextView headlineView;
        TextView reporterNameView;
        TextView reportedDateView;
        TextView idDateView;
        ImageView imageView;
    }
}
