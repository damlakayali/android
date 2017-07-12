package com.damlakayali.notdefterim;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by damla on 2.07.2017.
 */

public class MyListAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private List<Note> noteList;

    public MyListAdapter(Activity activity, List<Note> notes) {
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //countryList = countries;
        noteList= notes;
    }
    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.row_layout, null); // create layout from

        TextView textView = (TextView) vi.findViewById(R.id.rowNote); // user name
        ImageView img = (ImageView) vi.findViewById(R.id.iconList);
        Note note = noteList.get(position);
        textView.setText(note.getHeader());
         if(note.getPriority()==1){
             img.setImageResource(R.drawable.red_note);
         }
         else if (note.getPriority()==2) {
             img.setImageResource(R.drawable.yellow_note);
         }

         else if(note.getPriority()==3){
             img.setImageResource(R.drawable.green_note);
         }


        return vi;

    }
}
