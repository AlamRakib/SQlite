package com.example.sqlite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecordListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Model> recordList;


    public RecordListAdapter(Context context, int layout, ArrayList<Model> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class viewHolder {

        ImageView  imageView;
        TextView txtName, txtAge, txtPhone;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        viewHolder holder = new viewHolder();

        if(row==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(layout,null);
            holder.txtName = row.findViewById(R.id.txtName);
            holder.txtAge = row.findViewById(R.id.txtAge);
            holder.txtPhone = row.findViewById(R.id.txtPhone);
            holder.imageView = row.findViewById(R.id.imgIcon);
            row.setTag(holder);
        }
        else{

            holder = (viewHolder) row.getTag();
        }


        Model model = recordList.get(i);

        holder.txtName.setText(model.getName());
        holder.txtAge.setText(model.getAge());
        holder.txtPhone.setText(model.getPhone());

        byte[] recordImage = model.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage,0,recordImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
