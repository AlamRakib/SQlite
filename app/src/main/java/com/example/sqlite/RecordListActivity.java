package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecordListActivity extends AppCompatActivity {

    ListView mListView;
    ArrayList<Model> mList;
    RecordListAdapter mAdapter = null;

    ImageView imageViewIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new RecordListAdapter(this,R.layout.sample_view,mList);
        mListView.setAdapter(mAdapter);

        //get all data from sqlite
        Cursor cursor = MainActivity.mSQLiteHelper.getData("SELECT * FROM RECORD");
        mList.clear();
        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            String phone = cursor.getString(3);

            byte[] image = cursor.getBlob(4);
            //add to list
            mList.add(new Model(id,name,age, phone,image));
        }

        mAdapter.notifyDataSetChanged();
        if(mList.size()==0){

            //if there is no record in table of database which means listview is empty
            Toast.makeText(getApplicationContext(),"No Data Found",Toast.LENGTH_SHORT).show();
        }

       mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               //alert dialog to display options of Update and delete

               CharSequence[] items = {"Update", "Delete"};
               AlertDialog.Builder builder = new AlertDialog.Builder(RecordListActivity.this);

               builder.setTitle("Choose an action");
               builder.setItems(items, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                       if(i == 0)
                       {
                           Cursor c = MainActivity.mSQLiteHelper.getData("SELECT id FROM RECORD");
                           ArrayList<Integer> arrayList = new ArrayList<Integer>();
                           while (c.moveToNext()){
                               arrayList.add(c.getInt(0));

                           }
                           //show update dialog
                       }
                       if (i ==1)
                       {
                           //delete
                       }
                   }
               });

               builder.show();
               
           }
       });


    }



}